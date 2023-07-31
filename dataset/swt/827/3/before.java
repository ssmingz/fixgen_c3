class Image {
  public Image(Device device, ImageData image) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, image);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
