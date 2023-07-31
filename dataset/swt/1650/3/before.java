class PlaceHold {
  public ImageData getImageData() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int width = OS.CGImageGetWidth(handle);
    int height = OS.CGImageGetHeight(handle);
    int bpr = OS.CGImageGetBytesPerRow(handle);
    int bpp = OS.CGImageGetBitsPerPixel(handle);
    int dataSize = height * bpr;
    byte[] srcData = new byte[dataSize];
    OS.memmove(srcData, data, dataSize);
    PaletteData palette = new PaletteData(0xff0000, 0xff00, 0xff);
    ImageData data = new ImageData(width, height, bpp, palette);
    data.data = srcData;
    data.bytesPerLine = bpr;
    data.transparentPixel = transparentPixel;
    if ((transparentPixel == (-1)) && (type == SWT.ICON)) {
      int maskPad = 2;
      int maskBpl = ((((width + 7) / 8) + (maskPad - 1)) / maskPad) * maskPad;
      byte[] maskData = new byte[height * maskBpl];
      int offset = 0;
      int maskOffset = 0;
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          if (srcData[offset] != 0) {
            maskData[maskOffset + (x >> 3)] |= 1 << (7 - (x & 0x7));
          } else {
            maskData[maskOffset + (x >> 3)] &= ~(1 << (7 - (x & 0x7)));
          }
          offset += 4;
        }
        maskOffset += maskBpl;
      }
      data.maskData = maskData;
      data.maskPad = maskPad;
    }
    for (int i = 0; i < srcData.length; i += 4) {
      srcData[i] = 0;
    }
    data.alpha = alpha;
    if ((alpha == (-1)) && (alphaData != null)) {
      data.alphaData = new byte[alphaData.length];
      System.arraycopy(alphaData, 0, data.alphaData, 0, alphaData.length);
    }
    return data;
  }
}
