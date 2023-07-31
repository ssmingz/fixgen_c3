class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    if (txnObject == 0) {
      Rect rect = new Rect();
      OS.GetBestControlRect(handle, rect, null);
      width = rect.right - rect.left;
      height = rect.bottom - rect.top;
    } else if (OS.VERSION >= 0x1030) {
      int[] oDataHandle = new int[1];
      OS.TXNGetData(txnObject, kTXNStartOffset, kTXNEndOffset, oDataHandle);
      if (oDataHandle[0] != 0) {
        int length = OS.GetHandleSize(oDataHandle[0]);
        if (length != 0) {
          int[] ptr = new int[1];
          OS.HLock(oDataHandle[0]);
          OS.memcpy(ptr, oDataHandle[0], 4);
          int str = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, ptr[0], length / 2);
          if (str != 0) {
            float[] w = new float[1];
            float[] h = new float[1];
            HIThemeTextInfo info = new HIThemeTextInfo();
            info.state = OS.kThemeStateActive;
            if (font != null) {
              OS.TextFont(id);
              OS.TextFace(style);
              OS.TextSize(size);
              info.fontID = ((short) (OS.kThemeCurrentPortFont));
            } else {
              info.fontID = ((short) (defaultThemeFont()));
            }
            OS.HIThemeGetTextDimensions(str, wHint == SWT.DEFAULT ? 0 : wHint, info, w, h, null);
            OS.CFRelease(str);
            width = ((int) (w[0]));
            height = ((int) (h[0]));
          }
          OS.HUnlock(oDataHandle[0]);
        } else {
          Font font = getFont();
          FontInfo info = new FontInfo();
          OS.FetchFontInfo(font.id, font.size, font.style, info);
          int fontHeight = (info.ascent + info.descent) + info.leading;
          height = fontHeight;
        }
        OS.DisposeHandle(oDataHandle[0]);
      }
    } else {
      TXNLongRect oTextRect = new TXNLongRect();
      OS.TXNGetRectBounds(txnObject, null, null, oTextRect);
      width = oTextRect.right - oTextRect.left;
      height = oTextRect.bottom - oTextRect.top;
    }
    if (width <= 0) {
      width = DEFAULT_WIDTH;
    }
    if (height <= 0) {
      height = DEFAULT_HEIGHT;
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    Rectangle trim = computeTrim(0, 0, width, height);
    width = trim.width;
    height = trim.height;
    return new Point(width, height);
  }
}
