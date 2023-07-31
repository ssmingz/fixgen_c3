class Image {
  public Image(Device display, InputStream stream) {
    if (stream == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (display == null) {
      display = Display.getDefault();
    }
    init(display, new ImageData(stream));
  }
}
