class Cursor {
  public Cursor(Device device, ImageData source, int hotspotX, int hotspotY) {
    super(device);
    if (source == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if ((((hotspotX >= source.width) || (hotspotX < 0)) || (hotspotY >= source.height))
        || (hotspotY < 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    ImageData mask = source.getTransparencyMask();
    int[] result = Image.init(this.device, null, source, mask);
    int hBitmap = result[0];
    int hMask = result[1];
    ICONINFO info = new ICONINFO();
    info.fIcon = false;
    info.hbmColor = hBitmap;
    info.hbmMask = hMask;
    info.xHotspot = hotspotX;
    info.yHotspot = hotspotY;
    handle = OS.CreateIconIndirect(info);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.DeleteObject(hBitmap);
    OS.DeleteObject(hMask);
    isIcon = true;
    init();
  }
}
