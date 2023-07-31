class PlaceHold {
  void init(Device device, ImageData image) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    if (image == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int xDisplay = device.xDisplay;
    int drawable = OS.XDefaultRootWindow(xDisplay);
    int screenDepth = OS.XDefaultDepthOfScreen(OS.XDefaultScreenOfDisplay(xDisplay));
    int visual = OS.XDefaultVisual(xDisplay, OS.XDefaultScreen(xDisplay));
    int pixmap = OS.XCreatePixmap(xDisplay, drawable, image.width, image.height, screenDepth);
    if (pixmap == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int gc = OS.XCreateGC(xDisplay, pixmap, 0, null);
    int[] transPixel = null;
    if (image.transparentPixel != (-1)) {
      transPixel = new int[] {image.transparentPixel};
    }
    int error =
        putImage(
            image,
            0,
            0,
            image.width,
            image.height,
            0,
            0,
            image.width,
            image.height,
            xDisplay,
            visual,
            screenDepth,
            device.xcolors,
            transPixel,
            pixmap,
            gc);
    OS.XFreeGC(xDisplay, gc);
    if (error != 0) {
      OS.XFreePixmap(xDisplay, pixmap);
      SWT.error(error);
    }
    if ((image.getTransparencyType() == SWT.TRANSPARENCY_MASK)
        || (image.transparentPixel != (-1))) {
      if (image.transparentPixel != (-1)) {
        transparentPixel = transPixel[0];
      }
      ImageData maskImage = image.getTransparencyMask();
      int mask = OS.XCreatePixmap(xDisplay, drawable, image.width, image.height, 1);
      gc = OS.XCreateGC(xDisplay, mask, 0, null);
      error =
          putImage(
              maskImage,
              0,
              0,
              maskImage.width,
              maskImage.height,
              0,
              0,
              maskImage.width,
              maskImage.height,
              xDisplay,
              visual,
              screenDepth,
              device.xcolors,
              null,
              mask,
              gc);
      OS.XFreeGC(xDisplay, gc);
      if (error != 0) {
        OS.XFreePixmap(xDisplay, pixmap);
        OS.XFreePixmap(xDisplay, mask);
        SWT.error(error);
      }
      this.mask = mask;
      if (image.getTransparencyType() == SWT.TRANSPARENCY_MASK) {
        this.type = SWT.ICON;
      } else {
        this.type = SWT.BITMAP;
      }
    } else {
      this.type = SWT.BITMAP;
      this.mask = 0;
      this.alpha = image.alpha;
      if ((image.alpha == (-1)) && (image.alphaData != null)) {
        this.alphaData = new byte[image.alphaData.length];
        System.arraycopy(image.alphaData, 0, this.alphaData, 0, alphaData.length);
      }
    }
    this.pixmap = pixmap;
  }
}
