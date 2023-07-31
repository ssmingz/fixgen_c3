class PlaceHold {
  public void setRegion(Region region) {
    checkWidget();
    if ((style & SWT.NO_TRIM) == 0) {
      return;
    }
    int window = OS.GTK_WIDGET_WINDOW(shellHandle);
    int shape_region = (region == null) ? 0 : region.handle;
    OS.gdk_window_shape_combine_region(window, shape_region, 0, 0);
    this.region = region;
  }
}
