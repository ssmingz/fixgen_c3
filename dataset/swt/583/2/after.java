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
    this.font = hFont;
    redraw();
  }
}
