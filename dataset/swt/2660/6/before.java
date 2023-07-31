class PlaceHold {
  public static Font carbon_new(Device device, int handle, short style, float size) {
    if (device == null) {
      device = Device.getDevice();
    }
    Font font = new Font();
    font.handle = handle;
    font.style = style;
    font.size = size;
    font.device = device;
    return font;
  }
}
