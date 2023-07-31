class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((type != SWT.BITMAP) || (memGC != null)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int width = OS.CGImageGetWidth(handle);
    int height = OS.CGImageGetHeight(handle);
    int bpc = OS.CGImageGetBitsPerComponent(handle);
    int bpr = OS.CGImageGetBytesPerRow(handle);
    int colorspace = OS.CGImageGetColorSpace(handle);
    int context =
        OS.CGBitmapContextCreate(
            this.data, width, height, bpc, bpr, colorspace, kCGImageAlphaNoneSkipFirst);
    if (context == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.CGContextScaleCTM(context, 1, -1);
    OS.CGContextTranslateCTM(context, 0, -height);
    OS.CGContextSetShouldSmoothFonts(context, false);
    if (data != null) {
      int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
      if ((data.style & mask) == 0) {
        data.style |= SWT.LEFT_TO_RIGHT;
      }
      data.device = device;
      data.background = device.COLOR_WHITE.handle;
      data.foreground = device.COLOR_BLACK.handle;
      data.font = device.systemFont;
      data.image = this;
    }
    return context;
  }
}
