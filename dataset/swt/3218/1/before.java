class PlaceHold {
  public void setSpacing(int spacing) {
    checkLayout();
    if (spacing < 0) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.spacing == spacing) {
      return;
    }
    freeRuns();
    this.spacing = spacing;
  }
}
