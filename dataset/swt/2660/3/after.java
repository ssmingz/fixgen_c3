class PlaceHold {
  public static Font gtk_new(Device device, int handle) {
    Font font = new Font(device);
    font.handle = handle;
    return font;
  }
}
