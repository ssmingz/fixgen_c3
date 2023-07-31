class PlaceHold {
  public void addString(String string, float x, float y, Font font) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (font == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    move = false;
    GC.setCairoFont(handle, font);
    cairo_font_extents_t extents = new cairo_font_extents_t();
    Cairo.cairo_current_font_extents(handle, extents);
    double baseline = y + extents.ascent;
    Cairo.cairo_move_to(handle, x, baseline);
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    Cairo.cairo_text_path(handle, buffer);
  }
}
