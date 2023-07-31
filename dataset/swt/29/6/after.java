class Path {
  public Path(Device device) {
    super(device);
    this.device.checkCairo();
    int surface = Cairo.cairo_image_surface_create(CAIRO_FORMAT_ARGB32, 1, 1);
    if (surface == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    handle = Cairo.cairo_create(surface);
    Cairo.cairo_surface_destroy(surface);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    init();
  }
}
