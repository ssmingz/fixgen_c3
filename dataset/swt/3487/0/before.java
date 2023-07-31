class Image {
  public Image(Device device, Rectangle bounds) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (bounds == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, bounds.width, bounds.height);
  }
}
