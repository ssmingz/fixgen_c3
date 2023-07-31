class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (handle == null) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((type != SWT.BITMAP) || (memGC != null)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    NSBitmapImageRep rep = imageRep;
    if (imageRep.hasAlpha()) {
      int bpr = width * 4;
      rep = ((NSBitmapImageRep) (new NSBitmapImageRep().alloc()));
      int bitmapData = imageRep.bitmapData();
      if (data.bitmapDataAddress != 0) {
        OS.free(data.bitmapDataAddress);
      }
      data.bitmapDataAddress = OS.malloc(PTR_SIZEOF);
      OS.memmove(data.bitmapDataAddress, new int[] {bitmapData}, 4);
      rep =
          rep.initWithBitmapDataPlanes(
              data.bitmapDataAddress,
              width,
              height,
              8,
              3,
              false,
              false,
              NSDeviceRGBColorSpace,
              NSAlphaFirstBitmapFormat,
              bpr,
              32);
      rep.autorelease();
    }
    handle.setCacheMode(NSImageCacheNever);
    NSGraphicsContext context = NSGraphicsContext.graphicsContextWithBitmapImageRep(rep);
    NSGraphicsContext.setCurrentContext(context);
    NSAffineTransform transform = NSAffineTransform.transform();
    NSSize size = handle.size();
    transform.translateXBy(0, size.height);
    transform.scaleXBy(1, -1);
    transform.set();
    if (data != null) {
      int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
      if ((data.style & mask) == 0) {
        data.style |= SWT.LEFT_TO_RIGHT;
      }
      data.device = device;
      data.background = COLOR_WHITE.handle;
      data.foreground = COLOR_BLACK.handle;
      data.font = device.systemFont;
      data.image = this;
    }
    return context.id;
  }
}
