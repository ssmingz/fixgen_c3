class PlaceHold {
  public Rectangle getLineBounds(int lineIndex) {
    checkLayout();
    computeRuns();
    int lineCount = OS.pango_layout_get_line_count(layout);
    if (!((0 <= lineIndex) && (lineIndex < lineCount))) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    int iter = OS.pango_layout_get_iter(layout);
    if (iter == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    for (int i = 0; i < lineIndex; i++) {
      OS.pango_layout_iter_next_line(iter);
    }
    PangoRectangle rect = new PangoRectangle();
    OS.pango_layout_iter_get_line_extents(iter, null, rect);
    OS.pango_layout_iter_free(iter);
    int x = OS.PANGO_PIXELS(rect.x);
    int y = OS.PANGO_PIXELS(rect.y);
    int width = OS.PANGO_PIXELS(rect.width);
    int height = OS.PANGO_PIXELS(rect.height);
    if ((ascent != (-1)) && (descent != (-1))) {
      height = Math.max(height, ascent + descent);
    }
    if (OS.pango_context_get_base_dir(context) == OS.PANGO_DIRECTION_RTL) {
      x = (width() - x) - width;
    }
    x += Math.min(indent, wrapIndent);
    return new Rectangle(x, y, width, height);
  }
}
