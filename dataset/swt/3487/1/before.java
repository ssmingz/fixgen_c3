class Font {
  public Font(Device device, FontData fd) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (fd == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, fd.getName(), fd.getHeightF(), fd.getStyle(), fd.nsName);
  }
}
