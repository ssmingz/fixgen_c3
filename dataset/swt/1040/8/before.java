class PlaceHold {
  public void drawText(String string, int x, int y, int flags) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    try {
      if (focus(true, null)) {
        installFont();
        MacUtil.RGBForeColor(data.foreground);
        if ((flags & SWT.DRAW_TRANSPARENT) != 0) {
          OS.TextMode(((short) (srcOr)));
        } else if ((data.background & 0xff000000) == 0) {
          MacUtil.RGBBackColor(data.background);
          OS.TextMode(((short) (srcCopy)));
        } else {
          OS.TextMode(((short) (srcOr)));
        }
        short[] fontInfo = new short[4];
        OS.GetFontInfo(fontInfo);
        OS.MoveTo(((short) (x)), ((short) (y + fontInfo[0])));
        byte[] buffer = string.getBytes();
        OS.DrawText(buffer, ((short) (0)), ((short) (buffer.length)));
      }
    } finally {
      unfocus(true);
    }
  }
}
