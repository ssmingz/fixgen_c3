class Font {
  public Font(Device device, String name, int height, int style) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(device, name, height, style, null);
  }
}
