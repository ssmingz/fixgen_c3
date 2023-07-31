class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    int hFont = 0;
    if (font != null) {
      if (font.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      hFont = font.handle;
    }
    if (hFont == 0) {
      hFont = defaultFont();
    }
    OS.SendMessage(handle, WM_SETFONT, hFont, 1);
  }
}
