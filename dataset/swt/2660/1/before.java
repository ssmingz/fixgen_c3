class PlaceHold {
  public static Font wpf_new(Device device, int handle, double size) {
    if (device == null) {
      device = Device.getDevice();
    }
    Font font = new Font();
    font.handle = handle;
    font.size = size;
    font.device = device;
    return font;
  }
}
