class Image {
  public Image(Device device, Rectangle bounds) {
    super(device);
    if (bounds == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(bounds.width, bounds.height);
    init();
  }
}
