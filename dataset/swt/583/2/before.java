class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int newFont = -1;
    if (font != null) {
      parent.customDraw = true;
      newFont = font.handle;
    }
    this.font = newFont;
    redraw();
  }
}
