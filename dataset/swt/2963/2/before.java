class Image {
  public Image(Device display, Rectangle bounds) {
    if (bounds == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(display, bounds.width, bounds.height);
  }
}
