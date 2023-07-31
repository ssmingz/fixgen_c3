class ImageData {
  public ImageData(String filename) {
    ImageData[] data = new ImageLoader().load(filename);
    if (data.length < 1) {
      SWT.error(ERROR_INVALID_IMAGE);
    }
    ImageData i = data[0];
    setAllFields(
        i.width,
        i.height,
        i.depth,
        i.scanlinePad,
        i.bytesPerLine,
        i.data,
        i.palette,
        i.transparentPixel,
        i.maskData,
        i.maskPad,
        i.alphaData,
        i.alpha,
        i.type,
        i.x,
        i.y,
        i.disposalMethod,
        i.delayTime);
  }
}
