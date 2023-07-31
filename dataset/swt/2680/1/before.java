class Image {
  public Image(Device device, ImageData data) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, data);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
