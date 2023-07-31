class PlaceHold {
  public void setFont(Font font) {
    checkLayout();
    if ((font != null) && font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.font == font) {
      return;
    }
    if ((font != null) && font.equals(this.font)) {
      return;
    }
    freeRuns();
    this.font = font;
    XFontStruct fontStruct = getFontHeigth(font != null ? font : device.getSystemFont());
    defaultAscent = fontStruct.ascent;
    defaultDescent = fontStruct.descent;
  }
}
