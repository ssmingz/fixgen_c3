class PlaceHold {
  public ImageData getImageData() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      NSBitmapImageRep imageRep = getRepresentation();
      int width = imageRep.pixelsWide();
      int height = imageRep.pixelsHigh();
      int bpr = imageRep.bytesPerRow();
      int bpp = imageRep.bitsPerPixel();
      int dataSize = height * bpr;
      byte[] srcData = new byte[((int) (dataSize))];
      OS.memmove(srcData, imageRep.bitmapData(), dataSize);
      PaletteData palette = new PaletteData(0xff0000, 0xff00, 0xff);
      ImageData data =
          new ImageData(((int) (width)), ((int) (height)), ((int) (bpp)), palette, 4, srcData);
      data.bytesPerLine = ((int) (bpr));
      data.transparentPixel = transparentPixel;
      if ((transparentPixel == (-1)) && (type == SWT.ICON)) {
        int maskPad = 2;
        int maskBpl = ((((width + 7) / 8) + (maskPad - 1)) / maskPad) * maskPad;
        byte[] maskData = new byte[((int) (height * maskBpl))];
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
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}
