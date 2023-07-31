class PlaceHold {
  public void setClipping(Region region) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    setClipping(region != null ? region.handle : 0);
  }
}
