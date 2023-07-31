class PlaceHold {
  public ImageData getImageData() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    Rectangle srcBounds = getBounds();
    int width = srcBounds.width;
    int height = srcBounds.height;
    int xDisplay = device.xDisplay;
    int xSrcImagePtr = OS.XGetImage(xDisplay, pixmap, 0, 0, width, height, AllPlanes, ZPixmap);
    if (xSrcImagePtr == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    XImage xSrcImage = new XImage();
    OS.memmove(xSrcImage, xSrcImagePtr, sizeof);
    PaletteData palette = null;
    int length = xSrcImage.bytes_per_line * xSrcImage.height;
    byte[] srcData = new byte[length];
    OS.memmove(srcData, xSrcImage.data, length);
    switch (xSrcImage.bits_per_pixel) {
      case 1:
        palette = new PaletteData(new RGB[] {new RGB(0, 0, 0), new RGB(255, 255, 255)});
        break;
      case 4:
        SWT.error(ERROR_UNSUPPORTED_DEPTH);
      case 8:
        byte[] normPixel = new byte[256];
        for (int index = 0; index < normPixel.length; index++) {
          normPixel[index] = 0;
        }
        int numPixels = 1;
        int index = 0;
        for (int y = 0; y < xSrcImage.height; y++) {
          for (int x = 0; x < xSrcImage.bytes_per_line; x++) {
            int srcPixel = srcData[index + x] & 0xff;
            if ((srcPixel != 0) && (normPixel[srcPixel] == 0)) {
              normPixel[srcPixel] = ((byte) (numPixels++));
            }
            srcData[index + x] = normPixel[srcPixel];
          }
          index += xSrcImage.bytes_per_line;
        }
        int colormap = OS.XDefaultColormap(xDisplay, OS.XDefaultScreen(xDisplay));
        RGB[] rgbs = new RGB[numPixels];
        XColor color = new XColor();
        for (int srcPixel = 0; srcPixel < normPixel.length; srcPixel++) {
          if ((srcPixel == 0) || (normPixel[srcPixel] != 0)) {
            color.pixel = srcPixel;
            OS.XQueryColor(xDisplay, colormap, color);
            int rgbIndex = normPixel[srcPixel] & 0xff;
            rgbs[rgbIndex] =
                new RGB(
                    (color.red >> 8) & 0xff, (color.green >> 8) & 0xff, (color.blue >> 8) & 0xff);
          }
        }
        palette = new PaletteData(rgbs);
        break;
      case 16:
        if (xSrcImage.byte_order == OS.MSBFirst) {
          for (int i = 0; i < srcData.length; i += 2) {
            byte b = srcData[i];
            srcData[i] = srcData[i + 1];
            srcData[i + 1] = b;
          }
        }
        break;
      case 24:
        break;
      case 32:
        if (xSrcImage.byte_order == OS.LSBFirst) {
          for (int i = 0; i < srcData.length; i += 4) {
            byte b = srcData[i];
            srcData[i] = srcData[i + 3];
            srcData[i + 3] = b;
            b = srcData[i + 1];
            srcData[i + 1] = srcData[i + 2];
            srcData[i + 2] = b;
          }
        }
        break;
      default:
        SWT.error(ERROR_UNSUPPORTED_DEPTH);
    }
    if (palette == null) {
      int visual = OS.XDefaultVisual(xDisplay, OS.XDefaultScreen(xDisplay));
      Visual v = new Visual();
      OS.memmove(v, visual, sizeof);
      palette = new PaletteData(v.red_mask, v.green_mask, v.blue_mask);
    }
    ImageData data = new ImageData(width, height, xSrcImage.bits_per_pixel, palette, 4, srcData);
    if (((transparentPixel == (-1)) && (type == SWT.ICON)) && (mask != 0)) {
      int xMaskPtr = OS.XGetImage(xDisplay, mask, 0, 0, width, height, AllPlanes, ZPixmap);
      if (xMaskPtr == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      XImage xMask = new XImage();
      OS.memmove(xMask, xMaskPtr, sizeof);
      byte[] maskData = new byte[xMask.bytes_per_line * xMask.height];
      OS.memmove(maskData, xMask.data, maskData.length);
      OS.XDestroyImage(xMaskPtr);
      int maskPad = xMask.bitmap_pad / 8;
      data.maskPad = 2;
      maskData = ImageData.convertPad(maskData, width, height, 1, maskPad, data.maskPad);
      if (xMask.bitmap_bit_order == OS.LSBFirst) {
        for (int i = 0; i < maskData.length; i++) {
          byte b = maskData[i];
          maskData[i] =
              ((byte)
                  (((((((((b & 0x1) << 7) | ((b & 0x2) << 5)) | ((b & 0x4) << 3))
                                      | ((b & 0x8) << 1))
                                  | ((b & 0x10) >> 1))
                              | ((b & 0x20) >> 3))
                          | ((b & 0x40) >> 5))
                      | ((b & 0x80) >> 7)));
        }
      }
      data.maskData = maskData;
    }
    data.transparentPixel = transparentPixel;
    data.alpha = alpha;
    if ((alpha == (-1)) && (alphaData != null)) {
      data.alphaData = new byte[alphaData.length];
      System.arraycopy(alphaData, 0, data.alphaData, 0, alphaData.length);
    }
    OS.XDestroyImage(xSrcImagePtr);
    return data;
  }
}
