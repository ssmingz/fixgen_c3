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
    if (regionPath != null) {
      regionPath.release();
    }
    regionPath = getPath(region);
    if (region != null) {
      window.setBackgroundColor(NSColor.clearColor());
      window.setOpaque(false);
    } else {
      window.setBackgroundColor(NSColor.windowBackgroundColor());
      window.setOpaque(true);
    }
    window.contentView().setNeedsDisplay(true);
  }
}
