class PlaceHold {
  void init(Device device, int width, int height) {
    if ((width <= 0) || (height <= 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    this.device = device;
    type = SWT.BITMAP;
    int hDC = device.internal_new_GC(null);
    handle = OS.CreateCompatibleBitmap(hDC, width, height);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int memDC = OS.CreateCompatibleDC(hDC);
    int hOldBitmap = OS.SelectObject(memDC, handle);
    OS.PatBlt(memDC, 0, 0, width, height, PATCOPY);
    OS.SelectObject(memDC, hOldBitmap);
    OS.DeleteDC(memDC);
    device.internal_dispose_GC(hDC, null);
  }
}
