class Font {
  Font(Device device, String name, float height, int style) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, name, height, style, null);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
