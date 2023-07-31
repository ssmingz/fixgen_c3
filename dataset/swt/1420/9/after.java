class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    Font oldFont = this.font;
    if (oldFont == font) {
      return;
    }
    this.font = font;
    if ((oldFont != null) && oldFont.equals(font)) {
      return;
    }
    if (font != null) {
      parent.setCustomDraw(true);
    }
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    if (((parent.style & SWT.VIRTUAL) == 0) && cached) {
      int itemIndex = parent.indexOf(this);
      if (itemIndex != (-1)) {
        long hwnd = parent.handle;
        LVITEM lvItem = new LVITEM();
        lvItem.mask = OS.LVIF_TEXT;
        lvItem.iItem = itemIndex;
        lvItem.pszText = OS.LPSTR_TEXTCALLBACK;
        OS.SendMessage(hwnd, LVM_SETITEM, 0, lvItem);
        cached = false;
      }
    }
    parent.setScrollWidth(this, false);
    redraw();
  }
}
