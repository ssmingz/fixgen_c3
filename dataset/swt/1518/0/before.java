class PlaceHold {
  int computePoints(LOGFONT logFont) {
    int hDC = internal_new_GC(null);
    int logPixelsY = OS.GetDeviceCaps(hDC, LOGPIXELSY);
    int pixels = 0;
    if (logFont.lfHeight > 0) {
      int hFont = OS.CreateFontIndirect(logFont);
      int oldFont = OS.SelectObject(hDC, hFont);
      TEXTMETRIC lptm = new TEXTMETRIC();
      OS.GetTextMetrics(hDC, lptm);
      OS.SelectObject(hDC, oldFont);
      OS.DeleteObject(hFont);
      pixels = logFont.lfHeight - lptm.tmInternalLeading;
    } else {
      pixels = -logFont.lfHeight;
    }
    internal_dispose_GC(hDC, null);
    return Compatibility.round(pixels * 72, logPixelsY);
  }
}
