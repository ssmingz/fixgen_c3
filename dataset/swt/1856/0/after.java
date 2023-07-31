class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (pixmap == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((type != SWT.BITMAP) || (memGC != null)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int xDisplay = device.xDisplay;
    int xGC = OS.XCreateGC(xDisplay, pixmap, 0, null);
    if (xGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
      if ((data.style & mask) == 0) {
        data.style |= SWT.LEFT_TO_RIGHT;
      }
      data.device = device;
      data.display = xDisplay;
      data.drawable = pixmap;
      data.font = device.systemFont;
      data.colormap = OS.XDefaultColormap(xDisplay, OS.XDefaultScreen(xDisplay));
      data.image = this;
    }
    return xGC;
  }
}
