class Image {
  public Image(Device device, Image srcImage, int flag) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
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
    this.device = device;
    this.type = srcImage.type;
    int width = OS.CGImageGetWidth(srcImage.handle);
    int height = OS.CGImageGetHeight(srcImage.handle);
    int bpr = OS.CGImageGetBytesPerRow(srcImage.handle);
    int bpc = OS.CGImageGetBitsPerComponent(srcImage.handle);
    int bpp = OS.CGImageGetBitsPerPixel(srcImage.handle);
    int colorspace = OS.CGImageGetColorSpace(srcImage.handle);
    int alphaInfo = OS.kCGImageAlphaNoneSkipFirst;
    alphaInfo = OS.CGImageGetAlphaInfo(srcImage.handle);
    transparentPixel = srcImage.transparentPixel;
    alpha = srcImage.alpha;
    if (srcImage.alphaData != null) {
      alphaData = new byte[srcImage.alphaData.length];
      System.arraycopy(srcImage.alphaData, 0, alphaData, 0, alphaData.length);
    }
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
    handle =
        OS.CGImageCreate(
            width, height, bpc, bpp, bpr, colorspace, alphaInfo, provider, null, true, 0);
    OS.CGDataProviderRelease(provider);
    if (handle == 0) {
      OS.DisposePtr(data);
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.memcpy(data, srcImage.data, dataSize);
    if (flag == SWT.IMAGE_COPY) {
      return;
    }
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
          byte[] line = new byte[bpr];
          for (int y = 0; y < height; y++) {
            OS.memcpy(line, data + (y * bpr), bpr);
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
            OS.memcpy(data + (y * bpr), line, bpr);
          }
          break;
        }
      case SWT.IMAGE_GRAY:
        {
          byte[] line = new byte[bpr];
          for (int y = 0; y < height; y++) {
            OS.memcpy(line, data + (y * bpr), bpr);
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
            OS.memcpy(data + (y * bpr), line, bpr);
          }
          break;
        }
    }
  }
}
