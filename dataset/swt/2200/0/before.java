class PlaceHold {
  void init(int width, int height) {
    if ((width <= 0) || (height <= 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    this.type = SWT.BITMAP;
    this.width = width;
    this.height = height;
    handle = ((NSImage) (new NSImage().alloc()));
    NSSize size = new NSSize();
    size.width = width;
    size.height = height;
    handle = handle.initWithSize(size);
    NSBitmapImageRep rep = ((NSBitmapImageRep) (new NSBitmapImageRep().alloc()));
    rep =
        rep.initWithBitmapDataPlanes(
            0,
            width,
            height,
            8,
            3,
            false,
            false,
            NSDeviceRGBColorSpace,
            OS.NSAlphaFirstBitmapFormat | OS.NSAlphaNonpremultipliedBitmapFormat,
            width * 4,
            32);
    OS.memset(rep.bitmapData(), 0xff, (width * height) * 4);
    handle.addRepresentation(rep);
    rep.release();
  }
}
