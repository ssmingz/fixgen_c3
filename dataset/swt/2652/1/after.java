class PlaceHold {
  void sendPaintItemEvent(TableItem item, NMLVCUSTOMDRAW nmcd) {
    int hDC = nmcd.hdc;
    GCData data = new GCData();
    data.device = display;
    data.font = Font.win32_new(display, item.fontHandle(nmcd.iSubItem));
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_STATE;
    lvItem.stateMask = OS.LVIS_SELECTED;
    lvItem.iItem = ((int) (nmcd.dwItemSpec));
    int result = OS.SendMessage(handle, LVM_GETITEM, 0, lvItem);
    boolean selected = (result != 0) && ((lvItem.state & OS.LVIS_SELECTED) != 0);
    boolean drawSelected = false;
    boolean drawBackground = false;
    boolean drawHot = false;
    if ((nmcd.iSubItem == 0) || ((style & SWT.FULL_SELECTION) != 0)) {
      drawHot = hotIndex == nmcd.dwItemSpec;
    }
    if (OS.IsWindowEnabled(handle)) {
      if (selected && ((nmcd.iSubItem == 0) || ((style & SWT.FULL_SELECTION) != 0))) {
        if (OS.GetFocus() == handle) {
          drawSelected = true;
          if (selectionForeground != (-1)) {
            data.foreground = selectionForeground;
          } else {
            data.foreground = OS.GetSysColor(COLOR_HIGHLIGHTTEXT);
          }
          data.background = OS.GetSysColor(COLOR_HIGHLIGHT);
        } else {
          drawSelected = (style & SWT.HIDE_SELECTION) == 0;
          data.foreground = OS.GetTextColor(hDC);
          data.background = OS.GetSysColor(COLOR_3DFACE);
        }
        if (explorerTheme && (selectionForeground == (-1))) {
          int clrText = (item.cellForeground != null) ? item.cellForeground[nmcd.iSubItem] : -1;
          if (clrText == (-1)) {
            clrText = item.foreground;
          }
          data.foreground = (clrText != (-1)) ? clrText : getForegroundPixel();
        }
      } else {
        int clrText = (item.cellForeground != null) ? item.cellForeground[nmcd.iSubItem] : -1;
        if (clrText == (-1)) {
          clrText = item.foreground;
        }
        int clrTextBk = (item.cellBackground != null) ? item.cellBackground[nmcd.iSubItem] : -1;
        if (clrTextBk == (-1)) {
          clrTextBk = item.background;
        }
        drawBackground = clrTextBk != (-1);
        if (clrTextBk == (-1)) {
          Control control = findBackgroundControl();
          if (control == null) {
            control = this;
          }
          clrTextBk = control.getBackgroundPixel();
        }
        data.foreground = (clrText != (-1)) ? clrText : OS.GetTextColor(hDC);
        data.background = (clrTextBk != (-1)) ? clrTextBk : OS.GetBkColor(hDC);
      }
    } else {
      data.foreground = OS.GetSysColor(COLOR_GRAYTEXT);
      data.background = OS.GetSysColor(COLOR_3DFACE);
    }
    data.uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
    int nSavedDC = OS.SaveDC(hDC);
    GC gc = GC.win32_new(hDC, data);
    RECT itemRect =
        item.getBounds(((int) (nmcd.dwItemSpec)), nmcd.iSubItem, true, true, false, false, hDC);
    Event event = new Event();
    event.item = item;
    event.gc = gc;
    event.index = nmcd.iSubItem;
    event.detail |= SWT.FOREGROUND;
    if (OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED) == nmcd.dwItemSpec) {
      if ((nmcd.iSubItem == 0) || ((style & SWT.FULL_SELECTION) != 0)) {
        if (handle == OS.GetFocus()) {
          int uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
          if ((uiState & OS.UISF_HIDEFOCUS) == 0) {
            event.detail |= SWT.FOCUSED;
          }
        }
      }
    }
    if (drawHot) {
      event.detail |= SWT.HOT;
    }
    if (drawSelected) {
      event.detail |= SWT.SELECTED;
    }
    if (drawBackground) {
      event.detail |= SWT.BACKGROUND;
    }
    event.x = itemRect.left;
    event.y = itemRect.top;
    event.width = itemRect.right - itemRect.left;
    event.height = itemRect.bottom - itemRect.top;
    RECT cellRect =
        item.getBounds(((int) (nmcd.dwItemSpec)), nmcd.iSubItem, true, true, true, true, hDC);
    int cellWidth = cellRect.right - cellRect.left;
    int cellHeight = cellRect.bottom - cellRect.top;
    gc.setClipping(cellRect.left, cellRect.top, cellWidth, cellHeight);
    sendEvent(PaintItem, event);
    event.gc = null;
    gc.dispose();
    OS.RestoreDC(hDC, nSavedDC);
  }
}
