class PlaceHold {
  void init(Device device, ImageData image) {
    if (image == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    int width = image.width;
    int height = image.height;
    PaletteData palette = image.palette;
    if (!((((((image.depth == 1) || (image.depth == 2)) || (image.depth == 4))
                || (image.depth == 8))
            && (!palette.isDirect))
        || ((image.depth == 8)
            || ((((image.depth == 16) || (image.depth == 24)) || (image.depth == 32))
                && palette.isDirect)))) {
      SWT.error(ERROR_UNSUPPORTED_DEPTH);
    }
    int dataSize = (width * height) * 4;
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
    int transparency = image.getTransparencyType();
    int alphaInfo =
        ((transparency == SWT.TRANSPARENCY_NONE) && (image.alpha == (-1)))
            ? OS.kCGImageAlphaNoneSkipFirst
            : OS.kCGImageAlphaFirst;
    handle =
        OS.CGImageCreate(
            width, height, 8, 32, width * 4, colorspace, alphaInfo, provider, null, true, 0);
    OS.CGDataProviderRelease(provider);
    if (handle == 0) {
      OS.DisposePtr(data);
      SWT.error(ERROR_NO_HANDLES);
    }
    int bpr = width * 4;
    byte[] buffer = new byte[dataSize];
    if (palette.isDirect) {
      ImageData.blit(
          BLIT_SRC,
          image.data,
          image.depth,
          image.bytesPerLine,
          image.getByteOrder(),
          0,
          0,
          width,
          height,
          palette.redMask,
          palette.greenMask,
          palette.blueMask,
          ALPHA_OPAQUE,
          null,
          0,
          0,
          0,
          buffer,
          32,
          bpr,
          MSB_FIRST,
          0,
          0,
          width,
          height,
          0xff0000,
          0xff00,
          0xff,
          false,
          false);
    } else {
      RGB[] rgbs = palette.getRGBs();
      int length = rgbs.length;
      byte[] srcReds = new byte[length];
      byte[] srcGreens = new byte[length];
      byte[] srcBlues = new byte[length];
      for (int i = 0; i < rgbs.length; i++) {
        RGB rgb = rgbs[i];
        if (rgb == null) {
          continue;
        }
        srcReds[i] = ((byte) (rgb.red));
        srcGreens[i] = ((byte) (rgb.green));
        srcBlues[i] = ((byte) (rgb.blue));
      }
      ImageData.blit(
          BLIT_SRC,
          image.data,
          image.depth,
          image.bytesPerLine,
          image.getByteOrder(),
          0,
          0,
          width,
          height,
          srcReds,
          srcGreens,
          srcBlues,
          ALPHA_OPAQUE,
          null,
          0,
          0,
          0,
          buffer,
          32,
          bpr,
          MSB_FIRST,
          0,
          0,
          width,
          height,
          0xff0000,
          0xff00,
          0xff,
          false,
          false);
    }
    if ((transparency == SWT.TRANSPARENCY_MASK) || (image.transparentPixel != (-1))) {
      this.type = (image.transparentPixel != (-1)) ? SWT.BITMAP : SWT.ICON;
      if (image.transparentPixel != (-1)) {
        int transRed = 0;
        int transGreen = 0;
        int transBlue = 0;
        if (palette.isDirect) {
          RGB rgb = palette.getRGB(image.transparentPixel);
          transRed = rgb.red;
          transGreen = rgb.green;
          transBlue = rgb.blue;
        } else {
          RGB[] rgbs = palette.getRGBs();
          if (image.transparentPixel < rgbs.length) {
            RGB rgb = rgbs[image.transparentPixel];
            transRed = rgb.red;
            transGreen = rgb.green;
            transBlue = rgb.blue;
          }
        }
        transparentPixel = ((transRed << 16) | (transGreen << 8)) | transBlue;
      }
      ImageData maskImage = image.getTransparencyMask();
      byte[] maskData = maskImage.data;
      int maskBpl = maskImage.bytesPerLine;
      int offset = 0;
      int maskOffset = 0;
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          buffer[offset] =
              ((maskData[maskOffset + (x >> 3)] & (1 << (7 - (x & 0x7)))) != 0)
                  ? ((byte) (0xff))
                  : 0;
          offset += 4;
        }
        maskOffset += maskBpl;
      }
    } else {
      this.type = SWT.BITMAP;
      if (image.alpha != (-1)) {
        this.alpha = image.alpha;
        byte a = ((byte) (this.alpha));
        for (int dataIndex = 0; dataIndex < buffer.length; dataIndex += 4) {
          buffer[dataIndex] = a;
        }
      } else if (image.alphaData != null) {
        this.alphaData = new byte[image.alphaData.length];
        System.arraycopy(image.alphaData, 0, this.alphaData, 0, alphaData.length);
        int offset = 0;
        int alphaOffset = 0;
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            buffer[offset] = alphaData[alphaOffset];
            offset += 4;
            alphaOffset += 1;
          }
        }
      }
    }
    OS.memmove(data, buffer, dataSize);
  }
}
