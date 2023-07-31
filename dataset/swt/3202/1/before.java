class Font {
  public Font(Device device, FontData fd) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, fd);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
