class PlaceHold {
  public void setDescent(int descent) {
    checkLayout();
    if (descent < (-1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.descent == descent) {
      return;
    }
    freeRuns();
    this.descent = descent;
  }
}
