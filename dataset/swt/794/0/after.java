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
    int hInst = OS.GetModuleHandle(null);
    if (OS.IsWinCE) {
      SWT.error(ERROR_NOT_IMPLEMENTED);
    }
    handle =
        OS.CreateCursor(
            hInst, hotspotX, hotspotY, source.width, source.height, source.data, mask.data);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
