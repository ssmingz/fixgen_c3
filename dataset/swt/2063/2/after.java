class PlaceHold {
  public void copyArea(Image image, int x, int y) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (image == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if ((image.type != SWT.BITMAP) || image.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (data.control != 0) {
      int[] offscreen = new int[1];
      OS.HIViewCreateOffscreenImage(data.control, 0, null, offscreen);
      copyArea(image, x, y, offscreen[0]);
      if (offscreen[0] != 0) {
        OS.CGImageRelease(offscreen[0]);
      }
    } else if (data.image != null) {
      copyArea(image, x, y, data.image.handle);
    } else if (data.window != 0) {
      int imageHandle = image.handle;
      CGRect rect = new CGRect();
      rect.x = x;
      rect.y = y;
      rect.width = OS.CGImageGetWidth(imageHandle);
      rect.height = OS.CGImageGetHeight(imageHandle);
      int[] displays = new int[16];
      int[] count = new int[1];
      if (OS.CGGetDisplaysWithRect(rect, displays.length, displays, count) != 0) {
        return;
      }
      for (int i = 0; i < count[0]; i++) {
        int display = displays[i];
        int address = OS.CGDisplayBaseAddress(display);
        if (address != 0) {
          int width = OS.CGDisplayPixelsWide(display);
          int height = OS.CGDisplayPixelsHigh(display);
          int bpr = OS.CGDisplayBytesPerRow(display);
          int bpp = OS.CGDisplayBitsPerPixel(display);
          int bps = OS.CGDisplayBitsPerSample(display);
          int provider = OS.CGDataProviderCreateWithData(0, address, bpr * height, 0);
          int srcImage =
              OS.CGImageCreate(
                  width,
                  height,
                  bps,
                  bpp,
                  bpr,
                  data.device.colorspace,
                  kCGImageAlphaNoneSkipFirst,
                  provider,
                  null,
                  true,
                  0);
          OS.CGDataProviderRelease(provider);
          copyArea(image, x, y, srcImage);
          if (srcImage != 0) {
            OS.CGImageRelease(srcImage);
          }
        }
      }
    }
  }
}
