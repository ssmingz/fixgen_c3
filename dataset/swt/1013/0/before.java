class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.font == font) {
      return;
    }
    if ((this.font != null) && this.font.equals(font)) {
      return;
    }
    this.font = font;
    clearTextWidthCache();
    redraw();
  }
}
