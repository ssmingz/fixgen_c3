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
      int display = data.display;
      XGCValues values = new XGCValues();
      OS.XGetGCValues(display, handle, GCForeground, values);
      XColor color = new XColor();
      color.pixel = values.foreground;
      OS.XQueryColor(display, data.colormap, color);
      Cairo.cairo_set_rgb_color(
          cairo,
          (color.red & 0xffff) / ((float) (0xffff)),
          (color.green & 0xffff) / ((float) (0xffff)),
          (color.blue & 0xffff) / ((float) (0xffff)));
    }
    data.foregroundPattern = pattern;
  }
}
