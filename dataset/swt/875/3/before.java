class PlaceHold {
  public void setFont(int index, Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    int hFont = -1;
    if (font != null) {
      parent.customDraw = true;
      hFont = font.handle;
    }
    if (cellFont == null) {
      cellFont = new int[count];
      for (int i = 0; i < count; i++) {
        cellFont[i] = -1;
      }
    }
    if (cellFont[index] == hFont) {
      return;
    }
    cellFont[index] = hFont;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    if (index == 0) {
      if (this != parent.currentItem) {
        int hwnd = parent.handle;
        TVITEM tvItem = new TVITEM();
        tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_TEXT;
        tvItem.hItem = handle;
        tvItem.pszText = OS.LPSTR_TEXTCALLBACK;
        OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
      }
    } else {
      redraw(index, true, false);
    }
  }
}
