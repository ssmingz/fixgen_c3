class PlaceHold {
  public void setFont(Font font) {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
    int fontList = 0;
    if (font != null) {
      fontList = font.handle;
    }
    if (fontList == 0) {
      fontList = defaultFont();
    }
    setFontList(fontList);
  }
}
