class PlaceHold {
  public static Font cocoa_new(Device device, NSFont handle) {
    Font font = new Font(device);
    font.handle = handle;
    return font;
  }
}
