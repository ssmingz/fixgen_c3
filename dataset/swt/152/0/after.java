class PlaceHold {
  static void setCairoFont(int cairo, Font font) {
    if ((font == null) || font.isDisposed()) {
      return;
    }
    setCairoFont(cairo, font.handle);
  }
}
