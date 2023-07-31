class PlaceHold {
  public void setForegroundPattern(Pattern pattern) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((pattern != null) && pattern.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    initCairo();
    int cairo = data.cairo;
    if (pattern != null) {
      Cairo.cairo_set_pattern(cairo, pattern.handle);
    } else {
      GdkGCValues values = new GdkGCValues();
      OS.gdk_gc_get_values(handle, values);
      GdkColor color = new GdkColor();
      color.pixel = values.foreground_pixel;
      int colormap = OS.gdk_colormap_get_system();
      OS.gdk_colormap_query_color(colormap, color.pixel, color);
      Cairo.cairo_set_rgb_color(
          cairo,
          (color.red & 0xffff) / ((float) (0xffff)),
          (color.green & 0xffff) / ((float) (0xffff)),
          (color.blue & 0xffff) / ((float) (0xffff)));
    }
    data.foregroundPattern = pattern;
  }
}
