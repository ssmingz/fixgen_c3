class PlaceHold {
  public static Font wpf_new(Device device, int handle, double size) {
    Font font = new Font(device);
    font.handle = handle;
    font.size = size;
    return font;
  }
}
