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
    if (index == 0) {
      if (((parent.style & SWT.VIRTUAL) == 0) && cached) {
        int itemIndex = parent.indexOf(this);
        if (itemIndex != (-1)) {
          int hwnd = parent.handle;
          LVITEM lvItem = new LVITEM();
          lvItem.mask = OS.LVIF_TEXT;
          lvItem.iItem = itemIndex;
          lvItem.pszText = OS.LPSTR_TEXTCALLBACK;
          OS.SendMessage(hwnd, LVM_SETITEM, 0, lvItem);
          cached = false;
        }
      }
      parent.setScrollWidth(this, false);
    }
    redraw();
  }
}
