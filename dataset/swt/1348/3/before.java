class PlaceHold {
  void init(Device device, int width, int height) {
    if ((width <= 0) || (height <= 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    this.device = device;
    this.type = SWT.BITMAP;
    int bpr = width * 4;
    int dataSize = height * bpr;
    data = OS.NewPtr(dataSize);
    if (data == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int provider = OS.CGDataProviderCreateWithData(0, data, dataSize, 0);
    if (provider == 0) {
      OS.DisposePtr(data);
      SWT.error(ERROR_NO_HANDLES);
    }
    int colorspace = device.colorspace;
    handle =
        OS.CGImageCreate(
            width,
            height,
            8,
            32,
            bpr,
            colorspace,
            kCGImageAlphaNoneSkipFirst,
            provider,
            null,
            false,
            0);
    OS.CGDataProviderRelease(provider);
    if (handle == 0) {
      OS.DisposePtr(data);
      SWT.error(ERROR_NO_HANDLES);
    }
    int bpc = OS.CGImageGetBitsPerComponent(handle);
    int context =
        OS.CGBitmapContextCreate(
            this.data, width, height, bpc, bpr, colorspace, kCGImageAlphaNoneSkipFirst);
    CGRect rect = new CGRect();
    rect.width = width;
    rect.height = height;
    OS.CGContextSetRGBFillColor(context, 1, 1, 1, 1);
    OS.CGContextFillRect(context, rect);
    OS.CGContextRelease(context);
  }
}
