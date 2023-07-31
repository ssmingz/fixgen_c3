class PlaceHold {
  public FontMetrics getFontMetrics() {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    try {
      focus(false, null);
      carbon_installFont();
      short[] fontInfo = new short[4];
      OS.GetFontInfo(fontInfo);
      byte[] s = "abcdefghijklmnopqrstuvwxyz".getBytes();
      int width = OS.TextWidth(s, ((short) (0)), ((short) (s.length))) / 26;
      return FontMetrics.carbon_new(
          fontInfo[0], fontInfo[1], width, fontInfo[3], fontInfo[0] + fontInfo[1]);
    } finally {
      unfocus(false);
    }
  }
}
