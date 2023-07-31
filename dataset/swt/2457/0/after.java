class PlaceHold {
  public void setRegion(Region region) {
    checkWidget();
    if ((region != null) && region.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    long hRegion = 0;
    if (region != null) {
      hRegion = OS.CreateRectRgn(0, 0, 0, 0);
      OS.CombineRgn(hRegion, region.handle, hRegion, RGN_OR);
    }
    OS.SetWindowRgn(handle, hRegion, true);
    this.region = region;
  }
}
