class Pattern {
  public Pattern(Device device, Image image) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (image == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (image.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    this.device = device;
    device.checkCairo();
    image.createSurface();
    handle = Cairo.cairo_pattern_create_for_surface(image.surface);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    Cairo.cairo_pattern_set_extend(handle, CAIRO_EXTEND_REPEAT);
    surface = image.surface;
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
