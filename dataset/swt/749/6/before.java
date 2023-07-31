class PlaceHold {
  public int internal_new_GC(GCData data) {
    int xGC = OS.XCreateGC(xDisplay, xDrawable, 0, null);
    if (xGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      data.device = this;
      data.display = xDisplay;
      data.drawable = xDrawable;
      data.fontList = defaultFont.handle;
      data.codePage = defaultFont.codePage;
      data.colormap = OS.XDefaultColormapOfScreen(xScreen);
      int defaultGC = OS.XDefaultGCOfScreen(xScreen);
      if (defaultGC != 0) {
        XGCValues values = new XGCValues();
        OS.XGetGCValues(xDisplay, defaultGC, OS.GCBackground | OS.GCForeground, values);
        data.foreground = values.foreground;
        data.background = values.background;
      }
    }
    return xGC;
  }
}
