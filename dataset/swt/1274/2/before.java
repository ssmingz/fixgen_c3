class PlaceHold {
  public int internal_new_GC(GCData data) {
    if ((type != SWT.BITMAP) || (memGC != null)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int hDC = device.internal_new_GC(null);
    int imageDC = OS.CreateCompatibleDC(hDC);
    device.internal_dispose_GC(hDC, null);
    if (imageDC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      data.device = device;
      data.image = this;
      data.hFont = device.getSystemFont().handle;
    }
    return imageDC;
  }
}
