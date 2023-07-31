class PlaceHold {
  public void setClipping(Region region) {
    checkWidget();
    if ((style & SWT.NO_TRIM) == 0) {
      return;
    }
    OS.SetWindowRgn(handle, region.handle, true);
  }
}
