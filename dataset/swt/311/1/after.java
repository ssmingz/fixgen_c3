class Font {
  public Font(Device device, String name, int height, int style) {
    super(device);
    init(name, height, style, null);
    init();
  }
}
