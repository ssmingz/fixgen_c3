class Image {
  public Image(Device device, String filename) {
    super(device);
    init(new ImageData(filename));
    init();
  }
}
