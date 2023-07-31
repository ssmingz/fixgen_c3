class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    if (font != null) {
      if (font.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
    }
    this.font = font;
    setFontStyle(font);
    redrawWidget(handle, false);
  }
}
