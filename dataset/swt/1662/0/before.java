class PlaceHold {
  public void setRegion(Region region) {
    checkWidget();
    if ((region != null) && region.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int window = OS.GTK_WIDGET_WINDOW(topHandle());
    int shape_region = (region == null) ? 0 : region.handle;
    OS.gdk_window_shape_combine_region(window, shape_region, 0, 0);
    this.region = region;
  }
}
