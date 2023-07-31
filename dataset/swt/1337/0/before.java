class Image {
  public Image(Device device, InputStream stream) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, new ImageData(stream));
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
