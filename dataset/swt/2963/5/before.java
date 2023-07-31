class Image {
  public Image(Device display, ImageData image) {
    if (image == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (display == null) {
      display = Display.getDefault();
    }
    init(display, image);
  }
}
