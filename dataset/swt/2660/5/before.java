class PlaceHold {
  public static Font motif_new(Device device, int handle) {
    if (device == null) {
      device = Device.getDevice();
    }
    Font font = new Font();
    font.device = device;
    font.handle = handle;
    font.codePage = getCodePage(device.xDisplay, handle);
    return font;
  }
}
