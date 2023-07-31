class PlaceHold {
  void drawWidget(GC gc, RECT clipRect) {
    int hTheme = 0;
    if (isAppThemed()) {
      hTheme = OS.OpenThemeData(handle, EXPLORERBAR);
    }
    if (hTheme != 0) {
      RECT rect = new RECT();
      OS.GetClientRect(handle, rect);
      OS.DrawThemeBackground(hTheme, gc.handle, EBP_HEADERBACKGROUND, 0, rect, clipRect);
    } else {
      drawBackground(gc.handle);
    }
    boolean drawFocus = false;
    if (handle == OS.GetFocus()) {
      int uiState = OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0);
      drawFocus = (uiState & OS.UISF_HIDEFOCUS) == 0;
    }
    int hCaptionFont = 0;
    int oldFont = 0;
    if (hTheme == 0) {
      if ((!OS.IsWinCE) && (hFont == 0)) {
        NONCLIENTMETRICS info =
            (OS.IsUnicode)
                ? ((NONCLIENTMETRICS) (new NONCLIENTMETRICSW()))
                : new NONCLIENTMETRICSA();
        info.cbSize = NONCLIENTMETRICS.sizeof;
        if (OS.SystemParametersInfo(SPI_GETNONCLIENTMETRICS, 0, info, 0)) {
          LOGFONT logFont =
              (OS.IsUnicode)
                  ? ((LOGFONT) (((NONCLIENTMETRICSW) (info)).lfCaptionFont))
                  : ((NONCLIENTMETRICSA) (info)).lfCaptionFont;
          hCaptionFont = OS.CreateFontIndirect(logFont);
          oldFont = OS.SelectObject(gc.handle, hCaptionFont);
        }
      }
    }
    for (int i = 0; i < itemCount; i++) {
      ExpandItem item = items[i];
      item.drawItem(gc, hTheme, clipRect, (item == focusItem) && drawFocus);
    }
    if (hCaptionFont != 0) {
      OS.SelectObject(gc.handle, oldFont);
      OS.DeleteObject(hCaptionFont);
    }
    if (hTheme != 0) {
      OS.CloseThemeData(hTheme);
    }
  }
}
