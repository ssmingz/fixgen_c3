class PlaceHold {
  LRESULT wmNotifyToolTip(NMTTCUSTOMDRAW nmcd, int lParam) {
    if (OS.IsWinCE) {
      return null;
    }
    switch (nmcd.dwDrawStage) {
      case OS.CDDS_PREPAINT:
        {
          if (hooks(EraseItem) || hooks(PaintItem)) {
            return new LRESULT(OS.CDRF_NOTIFYPOSTPAINT | OS.CDRF_NEWFONT);
          }
          break;
        }
      case OS.CDDS_POSTPAINT:
        {
          LVHITTESTINFO pinfo = new LVHITTESTINFO();
          int pos = OS.GetMessagePos();
          POINT pt = new POINT();
          OS.POINTSTOPOINT(pt, pos);
          OS.ScreenToClient(handle, pt);
          pinfo.x = pt.x;
          pinfo.y = pt.y;
          if (OS.SendMessage(handle, LVM_SUBITEMHITTEST, 0, pinfo) != (-1)) {
            TableItem item = _getItem(pinfo.iItem);
            int hDC = OS.GetDC(handle);
            int hFont = item.fontHandle(pinfo.iSubItem);
            if (hFont == (-1)) {
              hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
            }
            int oldFont = OS.SelectObject(hDC, hFont);
            boolean drawForeground = true;
            RECT cellRect =
                item.getBounds(pinfo.iItem, pinfo.iSubItem, true, true, false, false, hDC);
            if (hooks(EraseItem)) {
              Event event = sendEraseItemEvent(item, nmcd, pinfo.iSubItem, cellRect, hFont);
              if (isDisposed() || item.isDisposed()) {
                break;
              }
              if (event.doit) {
                drawForeground = (event.detail & SWT.FOREGROUND) != 0;
              } else {
                drawForeground = false;
              }
            }
            if (drawForeground) {
              int nSavedDC = OS.SaveDC(nmcd.hdc);
              int gridWidth = (getLinesVisible()) ? Table.GRID_WIDTH : 0;
              RECT insetRect = toolTipInset(cellRect);
              OS.SetWindowOrgEx(nmcd.hdc, insetRect.left, insetRect.top, null);
              GCData data = new GCData();
              data.device = display;
              data.foreground = OS.GetTextColor(nmcd.hdc);
              data.background = OS.GetBkColor(nmcd.hdc);
              data.hFont = hFont;
              GC gc = GC.win32_new(nmcd.hdc, data);
              int x = cellRect.left;
              if (pinfo.iSubItem != 0) {
                x -= gridWidth;
              }
              Image image = item.getImage(pinfo.iSubItem);
              if (image != null) {
                Rectangle rect = image.getBounds();
                RECT imageRect =
                    item.getBounds(pinfo.iItem, pinfo.iSubItem, false, true, false, false, hDC);
                Point size =
                    (imageList == null)
                        ? new Point(rect.width, rect.height)
                        : imageList.getImageSize();
                int y = imageRect.top;
                if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
                  y = y + Math.max(0, ((imageRect.bottom - imageRect.top) - size.y) / 2);
                }
                gc.drawImage(image, rect.x, rect.y, rect.width, rect.height, x, y, size.x, size.y);
                x += (size.x + INSET) + (pinfo.iSubItem == 0 ? -2 : 4);
              } else {
                x += INSET + 2;
              }
              String string = item.getText(pinfo.iSubItem);
              if (string != null) {
                int flags = (OS.DT_NOPREFIX | OS.DT_SINGLELINE) | OS.DT_VCENTER;
                TableColumn column = (columns != null) ? columns[pinfo.iSubItem] : null;
                if (column != null) {
                  if ((column.style & SWT.CENTER) != 0) {
                    flags |= OS.DT_CENTER;
                  }
                  if ((column.style & SWT.RIGHT) != 0) {
                    flags |= OS.DT_RIGHT;
                  }
                }
                TCHAR buffer = new TCHAR(getCodePage(), string, false);
                RECT textRect = new RECT();
                OS.SetRect(textRect, x, cellRect.top, cellRect.right, cellRect.bottom);
                OS.DrawText(nmcd.hdc, buffer, buffer.length(), textRect, flags);
              }
              gc.dispose();
              OS.RestoreDC(nmcd.hdc, nSavedDC);
            }
            if (hooks(PaintItem)) {
              RECT itemRect =
                  item.getBounds(pinfo.iItem, pinfo.iSubItem, true, true, false, false, hDC);
              sendPaintItemEvent(item, nmcd, pinfo.iSubItem, itemRect, hFont);
            }
            OS.SelectObject(hDC, oldFont);
            OS.ReleaseDC(handle, hDC);
          }
        }
    }
    return null;
  }
}
