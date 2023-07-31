class PlaceHold {
  public static Font photon_new(Device device, byte[] stem) {
    Font font = new Font(device);
    font.init(null, 0, 0, stem);
    return font;
  }
}
