class Font {
  Font(Device device, String name, float height, int style) {
    super(device);
    init(name, height, style, null);
    init();
  }
}
