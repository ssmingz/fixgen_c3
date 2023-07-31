class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    int fontList = 0;
    if (font != null) {
      if (font.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      fontList = font.handle;
    }
    if (fontList == 0) {
      fontList = defaultFont();
    }
    setFontList(fontList);
  }
}
