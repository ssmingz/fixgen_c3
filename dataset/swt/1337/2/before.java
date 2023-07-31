class Color {
  public Color(Device device, int red, int green, int blue) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, red, green, blue);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
