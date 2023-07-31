class Image {
  public Image(Device device, String filename) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, new ImageData(filename));
  }
}
