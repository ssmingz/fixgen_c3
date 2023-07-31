class PlaceHold {
  public void drawString(String string, int x, int y, boolean isTransparent) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    try {
      if (focus(true, null)) {
        carbon_installFont();
        MacUtil.RGBForeColor(data.foreground);
        if (isTransparent) {
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
