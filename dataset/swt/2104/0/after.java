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
          if (OS.SendMessage(itemToolTipHandle, TTM_GETCURRENTTOOL, 0, 0) != 0) {
            TOOLINFO lpti = new TOOLINFO();
            lpti.cbSize = TOOLINFO.sizeof;
            if (OS.SendMessage(itemToolTipHandle, TTM_GETCURRENTTOOL, 0, lpti) != 0) {
              int[] index = new int[1];
              TreeItem[] item = new TreeItem[1];
              RECT[] cellRect = new RECT[1];
              RECT[] itemRect = new RECT[1];
              int pos = OS.GetMessagePos();
              POINT pt = new POINT();
              OS.POINTSTOPOINT(pt, pos);
              OS.ScreenToClient(handle, pt);
              if (findCell(pt.x, pt.y, item, index, cellRect, itemRect)) {
                int hDC = OS.GetDC(handle);
                int hFont = item[0].fontHandle(index[0]);
                if (hFont == (-1)) {
                  hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
                }
                int oldFont = OS.SelectObject(hDC, hFont);
                LRESULT result = null;
                boolean drawForeground = true;
                cellRect[0] = item[0].getBounds(index[0], true, true, false, false, false, hDC);
                if (hooks(EraseItem)) {
                  Event event = sendEraseItemEvent(item[0], nmcd, index[0], cellRect[0], hFont);
                  if (isDisposed() || item[0].isDisposed()) {
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
                  RECT insetRect = toolTipInset(cellRect[0]);
                  OS.SetWindowOrgEx(nmcd.hdc, insetRect.left, insetRect.top, null);
                  GCData data = new GCData();
                  data.device = display;
                  data.foreground = OS.GetTextColor(nmcd.hdc);
                  data.background = OS.GetBkColor(nmcd.hdc);
                  data.font = Font.win32_new(display, hFont);
                  GC gc = GC.win32_new(nmcd.hdc, data);
                  int x = cellRect[0].left + INSET;
                  if (index[0] != 0) {
                    x -= gridWidth;
                  }
                  Image image = item[0].getImage(index[0]);
                  if ((image != null) || (index[0] == 0)) {
                    Point size = getImageSize();
                    RECT imageRect =
                        item[0].getBounds(index[0], false, true, false, false, false, hDC);
                    if (imageList == null) {
                      size.x = imageRect.right - imageRect.left;
                    }
                    if (image != null) {
                      Rectangle rect = image.getBounds();
                      gc.drawImage(
                          image,
                          rect.x,
                          rect.y,
                          rect.width,
                          rect.height,
                          x,
                          imageRect.top,
                          size.x,
                          size.y);
                      x += INSET + (index[0] == 0 ? 1 : 0);
                    }
                    x += size.x;
                  } else {
                    x += INSET;
                  }
                  String string = item[0].getText(index[0]);
                  if (string != null) {
                    int flags = (OS.DT_NOPREFIX | OS.DT_SINGLELINE) | OS.DT_VCENTER;
                    TreeColumn column = (columns != null) ? columns[index[0]] : null;
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
                    OS.SetRect(textRect, x, cellRect[0].top, cellRect[0].right, cellRect[0].bottom);
                    OS.DrawText(nmcd.hdc, buffer, buffer.length(), textRect, flags);
                  }
                  gc.dispose();
                  OS.RestoreDC(nmcd.hdc, nSavedDC);
                }
                if (hooks(PaintItem)) {
                  itemRect[0] = item[0].getBounds(index[0], true, true, false, false, false, hDC);
                  sendPaintItemEvent(item[0], nmcd, index[0], itemRect[0], hFont);
                }
                OS.SelectObject(hDC, oldFont);
                OS.ReleaseDC(handle, hDC);
                if (result != null) {
                  return result;
                }
              }
              break;
            }
          }
          break;
        }
    }
    return null;
  }
}
