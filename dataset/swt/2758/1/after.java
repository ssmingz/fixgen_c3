class PlaceHold {
  public void drawPath(Path path) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (path == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (path.handle == 0) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    initCairo();
    checkGC(DRAW);
    int cairo = data.cairo;
    Cairo.cairo_save(cairo);
    double xOffset = data.cairoXoffset;
    double yOffset = data.cairoYoffset;
    Cairo.cairo_translate(cairo, xOffset, yOffset);
    int copy = Cairo.cairo_copy_path(path.handle);
    if (copy == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    Cairo.cairo_append_path(cairo, copy);
    Cairo.cairo_path_destroy(copy);
    Cairo.cairo_stroke(cairo);
    Cairo.cairo_restore(cairo);
  }
}
