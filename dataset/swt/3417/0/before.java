class PlaceHold {
  public int internal_new_GC(GCData data) {
    if ((type != SWT.BITMAP) || (memGC != null)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int xDisplay = device.xDisplay;
    int xGC = OS.XCreateGC(xDisplay, pixmap, 0, null);
    if (xGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      data.device = device;
      data.display = xDisplay;
      data.drawable = pixmap;
      data.fontList = device.getSystemFont().handle;
      data.colormap = OS.XDefaultColormap(xDisplay, OS.XDefaultScreen(xDisplay));
      data.image = this;
    }
    return xGC;
  }
}
