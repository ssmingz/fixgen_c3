class PlaceHold {
  public void intersect(Region region) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (region == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (region.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    OS.SectRgn(handle, region.handle, handle);
  }
}
