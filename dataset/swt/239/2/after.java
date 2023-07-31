class PlaceHold {
  public void setRegion(Region region) {
    checkWidget();
    if ((style & SWT.NO_TRIM) == 0) {
      return;
    }
    if ((region != null) && region.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int hRegion = 0;
    if (region != null) {
      hRegion = OS.CreateRectRgn(0, 0, 0, 0);
      OS.CombineRgn(hRegion, region.handle, hRegion, RGN_OR);
    }
    OS.SetWindowRgn(handle, hRegion, true);
    this.region = region;
  }
}
