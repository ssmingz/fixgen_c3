class Image {
  public Image(Device display, String filename) {
    if (filename == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (display == null) {
      display = Display.getDefault();
    }
    init(display, new ImageData(filename));
  }
}
