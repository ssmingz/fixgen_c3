class Image {
  public Image(Device device, Image srcImage, int flag) {
    super(device);
    if (srcImage == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (srcImage.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    switch (flag) {
      case SWT.IMAGE_COPY:
      case SWT.IMAGE_DISABLE:
      case SWT.IMAGE_GRAY:
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      device = this.device;
      this.type = srcImage.type;
      NSSize size = srcImage.handle.size();
      int width = ((int) (size.width));
      int height = ((int) (size.height));
      NSBitmapImageRep srcRep = srcImage.getRepresentation();
      int bpr = srcRep.bytesPerRow();
      transparentPixel = srcImage.transparentPixel;
      alpha = srcImage.alpha;
      if (srcImage.alphaData != null) {
        alphaData = new byte[srcImage.alphaData.length];
        System.arraycopy(srcImage.alphaData, 0, alphaData, 0, alphaData.length);
      }
      handle = ((NSImage) (new NSImage().alloc()));
      handle = handle.initWithSize(size);
      NSBitmapImageRep rep = ((NSBitmapImageRep) (new NSBitmapImageRep().alloc()));
      rep =
          rep.initWithBitmapDataPlanes(
              0,
              width,
              height,
              srcRep.bitsPerSample(),
              srcRep.samplesPerPixel(),
              srcRep.samplesPerPixel() == 4,
              srcRep.isPlanar(),
              NSDeviceRGBColorSpace,
              OS.NSAlphaFirstBitmapFormat | OS.NSAlphaNonpremultipliedBitmapFormat,
              srcRep.bytesPerRow(),
              srcRep.bitsPerPixel());
      handle.addRepresentation(rep);
      rep.release();
      handle.setCacheMode(NSImageCacheNever);
      int data = rep.bitmapData();
      OS.memmove(data, srcRep.bitmapData(), (width * height) * 4);
      if (flag != SWT.IMAGE_COPY) {
        switch (flag) {
          case SWT.IMAGE_DISABLE:
            {
              Color zeroColor = device.getSystemColor(COLOR_WIDGET_NORMAL_SHADOW);
              RGB zeroRGB = zeroColor.getRGB();
              byte zeroRed = ((byte) (zeroRGB.red));
              byte zeroGreen = ((byte) (zeroRGB.green));
              byte zeroBlue = ((byte) (zeroRGB.blue));
              Color oneColor = device.getSystemColor(COLOR_WIDGET_BACKGROUND);
              RGB oneRGB = oneColor.getRGB();
              byte oneRed = ((byte) (oneRGB.red));
              byte oneGreen = ((byte) (oneRGB.green));
              byte oneBlue = ((byte) (oneRGB.blue));
              byte[] line = new byte[((int) (bpr))];
              for (int y = 0; y < height; y++) {
                OS.memmove(line, data + (y * bpr), bpr);
                int offset = 0;
                for (int x = 0; x < width; x++) {
                  int red = line[offset + 1] & 0xff;
                  int green = line[offset + 2] & 0xff;
                  int blue = line[offset + 3] & 0xff;
                  int intensity = ((red * red) + (green * green)) + (blue * blue);
                  if (intensity < 98304) {
                    line[offset + 1] = zeroRed;
                    line[offset + 2] = zeroGreen;
                    line[offset + 3] = zeroBlue;
                  } else {
                    line[offset + 1] = oneRed;
                    line[offset + 2] = oneGreen;
                    line[offset + 3] = oneBlue;
                  }
                  offset += 4;
                }
                OS.memmove(data + (y * bpr), line, bpr);
              }
              break;
            }
          case SWT.IMAGE_GRAY:
            {
              byte[] line = new byte[((int) (bpr))];
              for (int y = 0; y < height; y++) {
                OS.memmove(line, data + (y * bpr), bpr);
                int offset = 0;
                for (int x = 0; x < width; x++) {
                  int red = line[offset + 1] & 0xff;
                  int green = line[offset + 2] & 0xff;
                  int blue = line[offset + 3] & 0xff;
                  byte intensity =
                      ((byte)
                          ((((((((red + red) + green) + green) + green) + green) + green) + blue)
                              >> 3));
                  line[offset + 1] = line[offset + 2] = line[offset + 3] = intensity;
                  offset += 4;
                }
                OS.memmove(data + (y * bpr), line, bpr);
              }
              break;
            }
        }
      }
      init();
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}
