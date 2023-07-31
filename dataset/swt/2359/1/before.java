class Pattern {
  public Pattern(
      Device device,
      float x1,
      float y1,
      float x2,
      float y2,
      Color color1,
      int alpha1,
      Color color2,
      int alpha2) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (color1 == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (color1.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (color2 == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (color2.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    this.device = device;
    device.checkCairo();
    handle = Cairo.cairo_pattern_create_linear(x1, y1, x2, y2);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    GC.setCairoPatternColor(handle, 0, color1, alpha1);
    GC.setCairoPatternColor(handle, 1, color2, alpha2);
    Cairo.cairo_pattern_set_extend(handle, CAIRO_EXTEND_REPEAT);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
