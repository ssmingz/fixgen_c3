class PlaceHold {
  static void setCairoFont(int cairo, Font font) {
    FontData[] fds = font.getFontData();
    FontData fd = fds[0];
    int style = fd.getStyle();
    int slant = Cairo.CAIRO_FONT_SLANT_NORMAL;
    if ((style & SWT.ITALIC) != 0) {
      slant = Cairo.CAIRO_FONT_SLANT_ITALIC;
    }
    int weight = Cairo.CAIRO_FONT_WEIGHT_NORMAL;
    if ((style & SWT.BOLD) != 0) {
      weight = Cairo.CAIRO_FONT_WEIGHT_BOLD;
    }
    byte[] buffer = Converter.wcsToMbcs(null, fd.getName(), true);
    Cairo.cairo_select_font_face(cairo, buffer, slant, weight);
    Cairo.cairo_set_font_size(cairo, fd.getHeight());
  }
}
