class PlaceHold {
  public Font getFont() {
    checkWidget();
    if (font == null) {
      int hFont = defaultFont();
      return Font.win32_new(getDisplay(), hFont);
    }
    return font;
  }
}
