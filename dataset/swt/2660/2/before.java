class PlaceHold {
  public static Font win32_new(Device device, int handle) {
    if (device == null) {
      device = Device.getDevice();
    }
    Font font = new Font();
    font.handle = handle;
    font.device = device;
    return font;
  }
}
