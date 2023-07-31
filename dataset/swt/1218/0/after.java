class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    if ((style & SWT.SEPARATOR) != 0) {
      if ((style & SWT.HORIZONTAL) != 0) {
        width = DEFAULT_WIDTH;
        height = 3;
      } else {
        width = 3;
        height = DEFAULT_HEIGHT;
      }
    } else if (isImage && (image != null)) {
      Rectangle r = image.getBounds();
      width = r.width;
      height = r.height;
    } else {
      int length = text.length();
      if (length != 0) {
        int[] ptr = new int[1];
        OS.GetControlData(handle, ((short) (0)), kControlStaticTextCFStringTag, 4, ptr, null);
        if (ptr[0] != 0) {
          Point ioBounds = new Point();
          if (((style & SWT.WRAP) != 0) && (wHint != SWT.DEFAULT)) {
            ioBounds.h = ((short) (wHint));
          }
          if (font == null) {
            OS.GetThemeTextDimensions(
                ptr[0],
                ((short) (defaultThemeFont())),
                kThemeStateActive,
                ioBounds.h != 0,
                ioBounds,
                null);
          } else {
            int[] currentPort = new int[1];
            OS.GetPort(currentPort);
            OS.SetPortWindowPort(OS.GetControlOwner(handle));
            OS.TextFont(id);
            OS.TextFace(style);
            OS.TextSize(size);
            OS.GetThemeTextDimensions(
                ptr[0],
                ((short) (kThemeCurrentPortFont)),
                kThemeStateActive,
                ioBounds.h != 0,
                ioBounds,
                null);
            OS.SetPort(currentPort[0]);
          }
          width = ioBounds.h;
          height = ioBounds.v;
        }
        OS.CFRelease(ptr[0]);
      } else {
        Font font = getFont();
        FontInfo info = new FontInfo();
        OS.FetchFontInfo(font.id, font.size, font.style, info);
        int fontHeight = (info.ascent + info.descent) + info.leading;
        height = fontHeight;
      }
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    return new Point(width, height);
  }
}
