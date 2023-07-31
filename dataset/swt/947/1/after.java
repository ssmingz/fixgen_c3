class Cursor {
  public Cursor(Device device, ImageData source, ImageData mask, int hotspotX, int hotspotY) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    if (source == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (mask == null) {
      if (source.getTransparencyType() != SWT.TRANSPARENCY_MASK) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      mask = source.getTransparencyMask();
    }
    if ((mask.width != source.width) || (mask.height != source.height)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (mask.depth != 1) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (source.depth != 1) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((((hotspotX >= source.width) || (hotspotX < 0)) || (hotspotY >= source.height))
        || (hotspotY < 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    byte[] sourceData = new byte[source.data.length];
    byte[] maskData = new byte[mask.data.length];
    byte[] data = source.data;
    for (int i = 0; i < data.length; i++) {
      byte s = data[i];
      sourceData[i] =
          ((byte)
              (((((((((s & 0x80) >> 7) | ((s & 0x40) >> 5)) | ((s & 0x20) >> 3))
                                  | ((s & 0x10) >> 1))
                              | ((s & 0x8) << 1))
                          | ((s & 0x4) << 3))
                      | ((s & 0x2) << 5))
                  | ((s & 0x1) << 7)));
    }
    data = mask.data;
    for (int i = 0; i < data.length; i++) {
      byte s = data[i];
      maskData[i] =
          ((byte)
              (((((((((s & 0x80) >> 7) | ((s & 0x40) >> 5)) | ((s & 0x20) >> 3))
                                  | ((s & 0x10) >> 1))
                              | ((s & 0x8) << 1))
                          | ((s & 0x4) << 3))
                      | ((s & 0x2) << 5))
                  | ((s & 0x1) << 7)));
    }
    int xDisplay = device.xDisplay;
    int drawable = OS.XDefaultRootWindow(xDisplay);
    int sourcePixmap =
        OS.XCreateBitmapFromData(xDisplay, drawable, sourceData, source.width, source.height);
    int maskPixmap =
        OS.XCreateBitmapFromData(xDisplay, drawable, maskData, source.width, source.height);
    int screenNum = OS.XDefaultScreen(xDisplay);
    XColor foreground = new XColor();
    foreground.pixel = OS.XBlackPixel(xDisplay, screenNum);
    foreground.red = foreground.green = foreground.blue = 0;
    XColor background = new XColor();
    background.pixel = OS.XWhitePixel(xDisplay, screenNum);
    background.red = background.green = background.blue = ((short) (0xffff));
    handle =
        OS.XCreatePixmapCursor(
            xDisplay, sourcePixmap, maskPixmap, foreground, background, hotspotX, hotspotY);
    OS.XFreePixmap(xDisplay, sourcePixmap);
    OS.XFreePixmap(xDisplay, maskPixmap);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
