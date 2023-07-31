class Image {
  public Image(Device device, int width, int height) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, width, height);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
