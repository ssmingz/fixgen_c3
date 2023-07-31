class PlaceHold {
  int EnumFontFamProc(int lpelfe, int lpntme, int FontType, int lParam) {
    boolean isScalable = (FontType & OS.RASTER_FONTTYPE) == 0;
    boolean scalable = lParam == 1;
    if (isScalable == scalable) {
      if (nFonts == logFonts.length) {
        LOGFONT[] newLogFonts = new LOGFONT[logFonts.length + 128];
        System.arraycopy(logFonts, 0, newLogFonts, 0, nFonts);
        logFonts = newLogFonts;
        int[] newPixels = new int[newLogFonts.length];
        System.arraycopy(pixels, 0, newPixels, 0, nFonts);
        pixels = newPixels;
      }
      LOGFONT logFont = logFonts[nFonts];
      if (logFont == null) {
        logFont = (OS.IsUnicode) ? ((LOGFONT) (new LOGFONTW())) : new LOGFONTA();
      }
      OS.MoveMemory(logFont, lpelfe, sizeof);
      logFonts[nFonts] = logFont;
      if (logFont.lfHeight > 0) {
        OS.MoveMemory(metrics, lpntme, sizeof);
        pixels[nFonts] = logFont.lfHeight - metrics.tmInternalLeading;
      } else {
        pixels[nFonts] = -logFont.lfHeight;
      }
      nFonts++;
    }
    return 1;
  }
}
