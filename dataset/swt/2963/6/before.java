class Image {
  public Image(Device display, ImageData source, ImageData mask) {
    if (source == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (mask == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (display == null) {
      display = Display.getDefault();
    }
    if ((source.width != mask.width) || (source.height != mask.height)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (mask.depth != 1) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    ImageData image;
    if (source.depth != 1) {
      image =
          new ImageData(
              source.width,
              source.height,
              source.depth,
              source.palette,
              source.scanlinePad,
              source.data);
    } else {
      image = source.getTransparencyMask();
      int[] row = new int[source.width];
      for (int y = 0; y < source.height; y++) {
        source.getPixels(0, y, source.width, row, 0);
        image.setPixels(0, y, source.width, row, 0);
      }
    }
    image.type = SWT.ICON;
    image.maskPad = mask.scanlinePad;
    image.maskData = mask.data;
    init(display, image);
  }
}
