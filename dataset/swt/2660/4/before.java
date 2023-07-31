class PlaceHold {
  public static Font cocoa_new(Device device, NSFont handle) {
    if (device == null) {
      device = Device.getDevice();
    }
    Font font = new Font();
    font.handle = handle;
    font.device = device;
    return font;
  }
}
