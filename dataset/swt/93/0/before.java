class PlaceHold {
  public static Font photon_new(Device device, byte[] stem) {
    if (device == null) {
      device = Device.getDevice();
    }
    Font font = new Font();
    font.init(device, null, 0, 0, stem);
    return font;
  }
}
