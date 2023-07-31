class PlaceHold {
  int EnumFontFamProc(int lpelfe, int lpntme, int FontType, int lParam) {
    boolean isScalable = (FontType & OS.RASTER_FONTTYPE) == 0;
    if ((lParam == 1) != isScalable) {
      return 1;
    }
    if (nFonts == logFonts.length) {
      LOGFONT[] newLogFonts = new LOGFONT[logFonts.length + 128];
      System.arraycopy(logFonts, 0, newLogFonts, 0, nFonts);
      logFonts = newLogFonts;
    }
    LOGFONT logFont = logFonts[nFonts];
    if (logFont == null) {
      logFont = new LOGFONT();
    }
    OS.MoveMemory(logFont, lpelfe, sizeof);
    logFonts[nFonts++] = logFont;
    return 1;
  }
}
