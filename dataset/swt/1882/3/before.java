class PlaceHold {
  public void setAscent(int ascent) {
    checkLayout();
    if (ascent < (-1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.ascent == ascent) {
      return;
    }
    freeRuns();
    this.ascent = ascent;
  }
}
