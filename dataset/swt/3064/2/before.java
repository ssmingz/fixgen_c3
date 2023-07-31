class PlaceHold {
  public int internal_new_GC(GCData data) {
    int xDrawable = OS.XDefaultRootWindow(xDisplay);
    int xGC = OS.XCreateGC(xDisplay, xDrawable, 0, null);
    if (xGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.XSetSubwindowMode(xDisplay, xGC, IncludeInferiors);
    if (data != null) {
      data.device = this;
      data.display = xDisplay;
      data.drawable = xDrawable;
      data.fontList = defaultFontList;
      data.colormap = OS.XDefaultColormap(xDisplay, OS.XDefaultScreen(xDisplay));
    }
    return xGC;
  }
}
