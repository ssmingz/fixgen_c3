class Color {
  public Color(Device device, RGB rgb) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (rgb == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, rgb.red, rgb.green, rgb.blue);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
