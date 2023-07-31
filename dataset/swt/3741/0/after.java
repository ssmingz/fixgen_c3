class PlaceHold {
  public void setClipping(Region region) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((region != null) && region.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    setClipping(region != null ? region.handle : 0);
  }
}
