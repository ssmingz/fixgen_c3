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
      if (!(source.getTransparencyType() == SWT.TRANSPARENCY_MASK)) {
        SWT.error(ERROR_NULL_ARGUMENT);
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
      sourceData[i] = ((byte) (~sourceData[i]));
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
      maskData[i] = ((byte) (~maskData[i]));
    }
    int sourcePixmap = OS.gdk_bitmap_create_from_data(0, sourceData, source.width, source.height);
    if (sourcePixmap == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int maskPixmap = OS.gdk_bitmap_create_from_data(0, maskData, source.width, source.height);
    if (maskPixmap == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    GdkColor foreground = new GdkColor();
    foreground.red = 0;
    foreground.green = 0;
    foreground.blue = 0;
    GdkColor background = new GdkColor();
    background.red = ((short) (65535));
    background.green = ((short) (65535));
    background.blue = ((short) (65535));
    handle =
        OS.gdk_cursor_new_from_pixmap(
            maskPixmap, sourcePixmap, foreground, background, hotspotX, hotspotY);
    OS.g_object_unref(sourcePixmap);
    OS.g_object_unref(maskPixmap);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
