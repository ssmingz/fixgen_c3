class PlaceHold {
  void drawImage(
      Image srcImage,
      int srcX,
      int srcY,
      int srcWidth,
      int srcHeight,
      int destX,
      int destY,
      int destWidth,
      int destHeight,
      boolean simple) {
    if (data.updateClip) {
      setCGClipping();
    }
    int imageHandle = srcImage.handle;
    int imgWidth = OS.CGImageGetWidth(imageHandle);
    int imgHeight = OS.CGImageGetHeight(imageHandle);
    if (simple) {
      srcWidth = destWidth = imgWidth;
      srcHeight = destHeight = imgHeight;
    } else {
      simple =
          (((((srcX == 0) && (srcY == 0)) && (srcWidth == destWidth)) && (destWidth == imgWidth))
                  && (srcHeight == destHeight))
              && (destHeight == imgHeight);
      if (((srcX + srcWidth) > imgWidth) || ((srcY + srcHeight) > imgHeight)) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
    }
    OS.CGContextSaveGState(handle);
    OS.CGContextScaleCTM(handle, 1, -1);
    OS.CGContextTranslateCTM(handle, 0, -(destHeight + (2 * destY)));
    CGRect rect = new CGRect();
    rect.x = destX;
    rect.y = destY;
    rect.width = destWidth;
    rect.height = destHeight;
    if (simple) {
      OS.CGContextDrawImage(handle, rect, imageHandle);
    } else {
      int bpc = OS.CGImageGetBitsPerComponent(imageHandle);
      int bpp = OS.CGImageGetBitsPerPixel(imageHandle);
      int bpr = OS.CGImageGetBytesPerRow(imageHandle);
      int colorspace = OS.CGImageGetColorSpace(imageHandle);
      int alphaInfo = OS.CGImageGetAlphaInfo(imageHandle);
      int data = (srcImage.data + (srcY * bpr)) + (srcX * 4);
      int provider = OS.CGDataProviderCreateWithData(0, data, srcHeight * bpr, 0);
      if (provider != 0) {
        int subImage =
            OS.CGImageCreate(
                srcWidth,
                srcHeight,
                bpc,
                bpp,
                bpr,
                colorspace,
                alphaInfo,
                provider,
                null,
                false,
                0);
        OS.CGDataProviderRelease(provider);
        if (subImage != 0) {
          OS.CGContextDrawImage(handle, rect, subImage);
          OS.CGImageRelease(subImage);
        }
      }
    }
    OS.CGContextRestoreGState(handle);
    flush();
  }
}
