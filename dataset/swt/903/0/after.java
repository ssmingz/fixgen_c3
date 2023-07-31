class PlaceHold {
  public Font getFont() {
    checkWidget();
    if (font == null) {
      long hFont = defaultFont();
      return Font.win32_new(display, hFont);
    }
    return font;
  }
}
