class Image {
  public Image(Device display, Rectangle bounds) {
    if (bounds == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(display, bounds.width, bounds.height);
    if (pixmap == 0) {
      SWT.error(ERROR_CANNOT_BE_ZERO);
    }
  }
}
