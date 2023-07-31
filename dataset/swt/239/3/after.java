class PlaceHold {
  public void setRegion(Region region) {
    checkWidget();
    if ((style & SWT.NO_TRIM) == 0) {
      return;
    }
    if ((region != null) && region.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    this.region = region;
  }
}
