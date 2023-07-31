class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int hFont = -1;
    if (font != null) {
      parent.customDraw = true;
      hFont = font.handle;
    }
    if (this.font == hFont) {
      return;
    }
    this.font = hFont;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    if (((parent.style & SWT.VIRTUAL) == 0) && (!cached)) {
      return;
    }
    if (this != parent.currentItem) {
      int hwnd = parent.handle;
      TVITEM tvItem = new TVITEM();
      tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_TEXT;
      tvItem.hItem = handle;
      tvItem.pszText = OS.LPSTR_TEXTCALLBACK;
      OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
    }
  }
}
