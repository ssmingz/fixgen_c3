class ImageData {
  ImageData(
      int width,
      int height,
      int depth,
      PaletteData palette,
      int scanlinePad,
      byte[] data,
      int maskPad,
      byte[] maskData,
      byte[] alphaData,
      int alpha,
      int transparentPixel,
      int type,
      int x,
      int y,
      int disposalMethod,
      int delayTime) {
    if (palette == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (!(((((((depth == 1) || (depth == 2)) || (depth == 4)) || (depth == 8)) || (depth == 16))
            || (depth == 24))
        || (depth == 32))) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((width <= 0) || (height <= 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int bytesPerLine =
        (((((width * depth) + 7) / 8) + (scanlinePad - 1)) / scanlinePad) * scanlinePad;
    setAllFields(
        width,
        height,
        depth,
        scanlinePad,
        bytesPerLine,
        data != null ? data : new byte[bytesPerLine * height],
        palette,
        transparentPixel,
        maskData,
        maskPad,
        alphaData,
        alpha,
        type,
        x,
        y,
        disposalMethod,
        delayTime);
  }
}
