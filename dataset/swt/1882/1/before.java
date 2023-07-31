class PlaceHold {
  public void setWidth(int width) {
    checkLayout();
    if ((width < (-1)) || (width == 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.wrapWidth == width) {
      return;
    }
    freeRuns();
    this.wrapWidth = width;
  }
}
