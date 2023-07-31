class PlaceHold {
  public static Font carbon_new(Device device, int handle, short style, float size) {
    Font font = new Font(device);
    font.handle = handle;
    font.style = style;
    font.size = size;
    return font;
  }
}
