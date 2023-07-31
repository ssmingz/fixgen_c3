class PlaceHold {
  public void setFont(int index, Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    if (cellFont == null) {
      if (font == null) {
        return;
      }
      cellFont = new Font[count];
    }
    Font oldFont = cellFont[index];
    if (oldFont == font) {
      return;
    }
    cellFont[index] = font;
    if ((oldFont != null) && oldFont.equals(font)) {
      return;
    }
    if (font != null) {
      parent.setCustomDraw(true);
    }
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
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
    redraw(index, true, false);
  }
}
