class PlaceHold {
  void checkGDIP() {
    if (gdipToken != null) {
      return;
    }
    int oldErrorMode = OS.SetErrorMode(SEM_FAILCRITICALERRORS);
    try {
      int[] token = new int[1];
      GdiplusStartupInput input = new GdiplusStartupInput();
      input.GdiplusVersion = 1;
      if (Gdip.GdiplusStartup(token, input, 0) == 0) {
        gdipToken = token;
      }
    } catch (Throwable t) {
      SWT.error(ERROR_NO_GRAPHICS_LIBRARY, t, " [GDI+ is required]");
    } finally {
      OS.SetErrorMode(oldErrorMode);
    }
  }
}
