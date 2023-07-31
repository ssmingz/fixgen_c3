class Image {
  public Image(Device device, InputStream stream) {
    super(device);
    init(new ImageData(stream));
    init();
  }
}
