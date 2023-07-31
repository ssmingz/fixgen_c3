class PlaceHold {
  public void add(Region region) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (region == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (region.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    OS.UnionRgn(handle, region.handle, handle);
  }
}
