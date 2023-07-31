class PlaceHold {
  public Point stringExtent(String string) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (string.length() == 0) {
      return new Point(0, getFontHeight());
    }
    try {
      focus(false, null);
      carbon_installFont();
      byte[] s = string.getBytes();
      int width = OS.TextWidth(s, ((short) (0)), ((short) (s.length)));
      short[] fontInfo = new short[4];
      OS.GetFontInfo(fontInfo);
      return new Point(width, fontInfo[0] + fontInfo[1]);
    } finally {
      unfocus(false);
    }
  }
}
