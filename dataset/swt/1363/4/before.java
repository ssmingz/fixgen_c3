class PlaceHold {
  public int getOffset(int x, int y, int[] trailing) {
    checkLayout();
    computeRuns();
    if ((trailing != null) && (trailing.length < 1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (OS.pango_context_get_base_dir(context) == OS.PANGO_DIRECTION_RTL) {
      x = width() - x;
    }
    int iter = OS.pango_layout_get_iter(layout);
    if (iter == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    PangoRectangle rect = new PangoRectangle();
    do {
      OS.pango_layout_iter_get_line_extents(iter, null, rect);
      rect.y = OS.PANGO_PIXELS(rect.y);
      rect.height = OS.PANGO_PIXELS(rect.height);
      if ((rect.y <= y) && (y < (rect.y + rect.height))) {
        rect.x = OS.PANGO_PIXELS(rect.x);
        rect.width = OS.PANGO_PIXELS(rect.width);
        if (x >= (rect.x + rect.width)) {
          x = (rect.x + rect.width) - 1;
        }
        if (x < rect.x) {
          x = rect.x;
        }
        break;
      }
    } while (OS.pango_layout_iter_next_line(iter));
    OS.pango_layout_iter_free(iter);
    int[] index = new int[1];
    int[] piTrailing = new int[1];
    OS.pango_layout_xy_to_index(layout, x * OS.PANGO_SCALE, y * OS.PANGO_SCALE, index, piTrailing);
    int ptr = OS.pango_layout_get_text(layout);
    int offset = ((int) (OS.g_utf8_pointer_to_offset(ptr, ptr + index[0])));
    if (trailing != null) {
      trailing[0] = piTrailing[0];
    }
    return untranslateOffset(offset);
  }
}
