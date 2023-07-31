class PlaceHold {
  void checkGDIP() {
    if (gdipToken != null) {
      return;
    }
    int oldErrorMode = 0;
    if (!OS.IsWinCE) {
      oldErrorMode = OS.SetErrorMode(SEM_FAILCRITICALERRORS);
    }
    try {
      int[] token = new int[1];
      GdiplusStartupInput input = new GdiplusStartupInput();
      input.GdiplusVersion = 1;
      if (Gdip.GdiplusStartup(token, input, 0) == 0) {
        gdipToken = token;
        if (loadedFonts != null) {
          fontCollection = Gdip.PrivateFontCollection_new();
          if (fontCollection == 0) {
            SWT.error(ERROR_NO_HANDLES);
          }
          for (int i = 0; i < loadedFonts.length; i++) {
            String path = loadedFonts[i];
            if (path == null) {
              break;
            }
            int length = path.length();
            char[] buffer = new char[length + 1];
            path.getChars(0, length, buffer, 0);
            Gdip.PrivateFontCollection_AddFontFile(fontCollection, buffer);
          }
          loadedFonts = null;
        }
      }
    } catch (Throwable t) {
      SWT.error(ERROR_NO_GRAPHICS_LIBRARY, t, " [GDI+ is required]");
    } finally {
      if (!OS.IsWinCE) {
        OS.SetErrorMode(oldErrorMode);
      }
    }
  }
}
