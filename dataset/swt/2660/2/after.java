class PlaceHold {
  public static Font win32_new(Device device, int handle) {
    Font font = new Font(device);
    font.handle = handle;
    return font;
  }
}
