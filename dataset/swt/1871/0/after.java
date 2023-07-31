class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int height = 0;
    int width = 0;
    if ((wHint == SWT.DEFAULT) || (hHint == SWT.DEFAULT)) {
      if (itemCount > 0) {
        int hDC = OS.GetDC(handle);
        int hTheme = 0;
        if (isAppThemed()) {
          hTheme = display.hExplorerBarTheme();
        }
        int hCurrentFont = 0;
        int oldFont = 0;
        if (hTheme == 0) {
          if (hFont != 0) {
            hCurrentFont = hFont;
          } else if (!OS.IsWinCE) {
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
              hCurrentFont = OS.CreateFontIndirect(logFont);
            }
          }
          if (hCurrentFont != 0) {
            oldFont = OS.SelectObject(hDC, hCurrentFont);
          }
        }
        height += spacing;
        for (int i = 0; i < itemCount; i++) {
          ExpandItem item = items[i];
          height += item.getHeaderHeight();
          if (item.expanded) {
            height += item.height;
          }
          height += spacing;
          width = Math.max(width, item.getPreferredWidth(hTheme, hDC));
        }
        if (hCurrentFont != 0) {
          OS.SelectObject(hDC, oldFont);
          if (hCurrentFont != hFont) {
            OS.DeleteObject(hCurrentFont);
          }
        }
        OS.ReleaseDC(handle, hDC);
      }
    }
    if (width == 0) {
      width = DEFAULT_WIDTH;
    }
    if (height == 0) {
      height = DEFAULT_HEIGHT;
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    Rectangle trim = computeTrim(0, 0, width, height);
    return new Point(trim.width, trim.height);
  }
}
