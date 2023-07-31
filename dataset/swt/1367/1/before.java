class PlaceHold {
  public FontMetrics getFontMetrics() {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int context = data.context;
    int lang = OS.pango_context_get_language(context);
    int metrics = OS.pango_context_get_metrics(context, data.font, lang);
    FontMetrics fm = new FontMetrics();
    fm.ascent = OS.PANGO_PIXELS(OS.pango_font_metrics_get_ascent(metrics));
    fm.descent = OS.PANGO_PIXELS(OS.pango_font_metrics_get_descent(metrics));
    fm.averageCharWidth =
        OS.PANGO_PIXELS(OS.pango_font_metrics_get_approximate_char_width(metrics));
    fm.height = fm.ascent + fm.descent;
    OS.pango_font_metrics_unref(metrics);
    return fm;
  }
}
