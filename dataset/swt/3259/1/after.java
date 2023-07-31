class PlaceHold {
  public FontData open() {
    int hwndOwner = 0;
    if (parent != null) {
      hwndOwner = parent.handle;
    }
    int hHeap = OS.GetProcessHeap();
    CHOOSEFONT lpcf = new CHOOSEFONT();
    lpcf.lStructSize = CHOOSEFONT.sizeof;
    lpcf.hwndOwner = hwndOwner;
    lpcf.Flags = OS.CF_SCREENFONTS | OS.CF_EFFECTS;
    int lpLogFont = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, sizeof);
    if ((fontData != null) && (fontData.data != null)) {
      lpcf.Flags |= OS.CF_INITTOLOGFONTSTRUCT;
      OS.MoveMemory(lpLogFont, fontData.data, sizeof);
    }
    lpcf.lpLogFont = lpLogFont;
    fontData = null;
    if (OS.ChooseFont(lpcf)) {
      LOGFONT logFont = new LOGFONT();
      OS.MoveMemory(logFont, lpLogFont, sizeof);
      int hDC = OS.GetDC(0);
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
      OS.ReleaseDC(0, hDC);
      int points = Compatibility.round(pixels * 72, logPixelsY);
      fontData = FontData.win32_new(logFont, points);
    }
    if (lpLogFont != 0) {
      OS.HeapFree(hHeap, 0, lpLogFont);
    }
    return fontData;
  }
}
