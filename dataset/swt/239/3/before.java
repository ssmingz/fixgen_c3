class PlaceHold {
  public void setRegion(Region region) {
    checkWidget();
    if ((style & SWT.NO_TRIM) == 0) {
      return;
    }
    this.region = region;
  }
}
