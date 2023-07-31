class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (isDisposed()) {
      SWT.error(ERROR_DEVICE_DISPOSED);
    }
    int xDrawable = OS.XDefaultRootWindow(xDisplay);
    int xGC = OS.XCreateGC(xDisplay, xDrawable, 0, null);
    if (xGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.XSetSubwindowMode(xDisplay, xGC, IncludeInferiors);
    if (data != null) {
      int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
      if ((data.style & mask) == 0) {
        data.style |= SWT.LEFT_TO_RIGHT;
      }
      data.device = this;
      data.display = xDisplay;
      data.drawable = xDrawable;
      data.fontList = defaultFont.handle;
      data.codePage = defaultFont.codePage;
      data.colormap = OS.XDefaultColormap(xDisplay, OS.XDefaultScreen(xDisplay));
    }
    return xGC;
  }
}
