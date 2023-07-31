class PlaceHold {
  public Point getLocation(int offset, boolean trailing) {
    checkLayout();
    computeRuns();
    int length = text.length();
    if (!((0 <= offset) && (offset <= length))) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    offset = translateOffset(offset);
    int ptr = OS.pango_layout_get_text(layout);
    int byteOffset = ((int) (OS.g_utf8_offset_to_pointer(ptr, offset) - ptr));
    int strlen = OS.strlen(ptr);
    byteOffset = Math.min(byteOffset, strlen);
    PangoRectangle pos = new PangoRectangle();
    OS.pango_layout_index_to_pos(layout, byteOffset, pos);
    int x = (trailing) ? pos.x + pos.width : pos.x;
    int y = pos.y;
    x = OS.PANGO_PIXELS(x);
    if (OS.pango_context_get_base_dir(context) == OS.PANGO_DIRECTION_RTL) {
      x = width() - x;
    }
    x += Math.min(indent, wrapIndent);
    return new Point(x, OS.PANGO_PIXELS(y));
  }
}
