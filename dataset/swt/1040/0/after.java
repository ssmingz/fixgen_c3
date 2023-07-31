class PlaceHold {
  int getFontHeight() {
    try {
      focus(false, null);
      carbon_installFont();
      short[] fontInfo = new short[4];
      OS.GetFontInfo(fontInfo);
      return fontInfo[0] + fontInfo[1];
    } finally {
      unfocus(false);
    }
  }
}
