class PlaceHold {
  static void setCairoFont(int cairo, int font) {
    int family = OS.pango_font_description_get_family(font);
    int length = OS.strlen(family);
    byte[] buffer = new byte[length + 1];
    OS.memmove(buffer, family, length);
    double height = (OS.PANGO_PIXELS(OS.pango_font_description_get_size(font)) * 96) / 72;
    int pangoStyle = OS.pango_font_description_get_style(font);
    int pangoWeight = OS.pango_font_description_get_weight(font);
    int slant = Cairo.CAIRO_FONT_SLANT_NORMAL;
    if (pangoStyle == OS.PANGO_STYLE_ITALIC) {
      slant = Cairo.CAIRO_FONT_SLANT_ITALIC;
    }
    if (pangoStyle == OS.PANGO_STYLE_OBLIQUE) {
      slant = Cairo.CAIRO_FONT_SLANT_OBLIQUE;
    }
    int weight = Cairo.CAIRO_FONT_WEIGHT_NORMAL;
    if (pangoWeight == OS.PANGO_WEIGHT_BOLD) {
      weight = Cairo.CAIRO_FONT_WEIGHT_BOLD;
    }
    Cairo.cairo_select_font(cairo, buffer, slant, weight);
    Cairo.cairo_scale_font(cairo, height);
  }
}
