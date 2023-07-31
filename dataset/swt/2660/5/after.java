class PlaceHold {
  public static Font motif_new(Device device, int handle) {
    Font font = new Font(device);
    font.handle = handle;
    font.codePage = getCodePage(device.xDisplay, handle);
    return font;
  }
}
