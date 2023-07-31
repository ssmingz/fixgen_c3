class PlaceHold {
  LRESULT CDDS_SUBITEMPREPAINT(int wParam, int lParam) {
    int code = OS.CDRF_DODEFAULT;
    ignoreDraw = ignoreDrawSelected = false;
    NMLVCUSTOMDRAW nmcd = new NMLVCUSTOMDRAW();
    OS.MoveMemory(nmcd, lParam, sizeof);
    TableItem item = _getItem(nmcd.dwItemSpec);
    if (item == null) {
      return null;
    }
    if (OS.IsWindowVisible(handle)) {
      if (hooks(MeasureItem)) {
        sendMeasureItemEvent(item, nmcd.dwItemSpec, nmcd.iSubItem, nmcd.hdc);
        if (isDisposed() || item.isDisposed()) {
          return null;
        }
      }
      if (hooks(EraseItem)) {
        sendEraseItemEvent(item, nmcd, lParam);
        if (isDisposed() || item.isDisposed()) {
          return null;
        }
        code |= OS.CDRF_NOTIFYPOSTPAINT;
      }
      if (ignoreDraw || hooks(PaintItem)) {
        code |= OS.CDRF_NOTIFYPOSTPAINT;
      }
    }
    int hDC = nmcd.hdc;
    int hFont = (item.cellFont != null) ? item.cellFont[nmcd.iSubItem] : -1;
    if (hFont == (-1)) {
      hFont = item.font;
    }
    int clrText = (item.cellForeground != null) ? item.cellForeground[nmcd.iSubItem] : -1;
    if (clrText == (-1)) {
      clrText = item.foreground;
    }
    int clrTextBk = (item.cellBackground != null) ? item.cellBackground[nmcd.iSubItem] : -1;
    if (clrTextBk == (-1)) {
      clrTextBk = item.background;
    }
    if (OS.IsWindowVisible(handle)) {
      if (((!ignoreDraw) && (!ignoreDrawSelected)) && ((style & SWT.FULL_SELECTION) != 0)) {
        int bits = OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0);
        if ((bits & OS.LVS_EX_FULLROWSELECT) == 0) {
          LVITEM lvItem = new LVITEM();
          lvItem.mask = OS.LVIF_STATE;
          lvItem.stateMask = OS.LVIS_SELECTED;
          lvItem.iItem = nmcd.dwItemSpec;
          int result = OS.SendMessage(handle, LVM_GETITEM, 0, lvItem);
          if ((result != 0) && ((lvItem.state & OS.LVIS_SELECTED) != 0)) {
            if (OS.GetFocus() == handle) {
              clrText = OS.GetSysColor(COLOR_HIGHLIGHTTEXT);
              clrTextBk = OS.GetSysColor(COLOR_HIGHLIGHT);
            } else if ((style & SWT.HIDE_SELECTION) == 0) {
              clrTextBk = OS.GetSysColor(COLOR_3DFACE);
            }
            if ((clrTextBk != (-1)) && (nmcd.iSubItem == 0)) {
              RECT itemRect = new RECT();
              itemRect.left = OS.LVIR_SELECTBOUNDS;
              if (OS.SendMessage(handle, LVM_GETITEMRECT, nmcd.dwItemSpec, itemRect) != 0) {
                RECT headerRect = new RECT();
                int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
                if (OS.SendMessage(hwndHeader, HDM_GETITEMRECT, 0, headerRect) != 0) {
                  itemRect.left = itemRect.right;
                  itemRect.right = headerRect.right;
                  fillBackground(hDC, clrTextBk, itemRect);
                }
              }
            }
          }
        }
      }
    }
    boolean hasAttributes = true;
    if (((hFont == (-1)) && (clrText == (-1))) && (clrTextBk == (-1))) {
      if (((item.cellForeground == null) && (item.cellBackground == null))
          && (item.cellFont == null)) {
        int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
        int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
        if (count == 1) {
          hasAttributes = false;
        }
      }
    }
    if (hasAttributes) {
      if (hFont == (-1)) {
        hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
      }
      OS.SelectObject(hDC, hFont);
      if (OS.IsWindowEnabled(handle)) {
        nmcd.clrText = (clrText == (-1)) ? getForegroundPixel() : clrText;
        if (clrTextBk == (-1)) {
          Control control = findBackgroundControl();
          if (control == null) {
            control = this;
          }
          nmcd.clrTextBk =
              (control.backgroundImage == null) ? control.getBackgroundPixel() : OS.CLR_NONE;
        } else {
          nmcd.clrTextBk = clrTextBk;
        }
        OS.MoveMemory(lParam, nmcd, sizeof);
      }
      code |= OS.CDRF_NEWFONT;
    }
    return new LRESULT(code);
  }
}
