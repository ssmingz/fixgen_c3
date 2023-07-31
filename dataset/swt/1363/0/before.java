class PlaceHold {
  public Rectangle getBounds(int start, int end) {
    checkLayout();
    computeRuns();
    int length = text.length();
    if (length == 0) {
      return new Rectangle(0, 0, 0, 0);
    }
    if (start > end) {
      return new Rectangle(0, 0, 0, 0);
    }
    start = Math.min(Math.max(0, start), length - 1);
    end = Math.min(Math.max(0, end), length - 1);
    start = translateOffset(start);
    end = translateOffset(end);
    int ptr = OS.pango_layout_get_text(layout);
    int byteStart = ((int) (OS.g_utf8_offset_to_pointer(ptr, start) - ptr));
    int byteEnd = ((int) (OS.g_utf8_offset_to_pointer(ptr, end + 1) - ptr));
    int strlen = OS.strlen(ptr);
    byteStart = Math.min(byteStart, strlen);
    byteEnd = Math.min(byteEnd, strlen);
    int[] ranges = new int[] {byteStart, byteEnd};
    int clipRegion = OS.gdk_pango_layout_get_clip_region(layout, 0, 0, ranges, 1);
    if (clipRegion == 0) {
      return new Rectangle(0, 0, 0, 0);
    }
    GdkRectangle rect = new GdkRectangle();
    PangoRectangle pangoRect = new PangoRectangle();
    int iter = OS.pango_layout_get_iter(layout);
    if (iter == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int linesRegion = OS.gdk_region_new();
    if (linesRegion == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int lineEnd = 0;
    do {
      OS.pango_layout_iter_get_line_extents(iter, null, pangoRect);
      if (OS.pango_layout_iter_next_line(iter)) {
        lineEnd = OS.pango_layout_iter_get_index(iter) - 1;
      } else {
        lineEnd = strlen;
      }
      if (byteStart > lineEnd) {
        continue;
      }
      rect.x = OS.PANGO_PIXELS(pangoRect.x);
      rect.y = OS.PANGO_PIXELS(pangoRect.y);
      rect.width = OS.PANGO_PIXELS(pangoRect.width);
      rect.height = OS.PANGO_PIXELS(pangoRect.height);
      OS.gdk_region_union_with_rect(linesRegion, rect);
    } while ((lineEnd + 1) <= byteEnd);
    OS.gdk_region_intersect(clipRegion, linesRegion);
    OS.gdk_region_destroy(linesRegion);
    OS.pango_layout_iter_free(iter);
    OS.gdk_region_get_clipbox(clipRegion, rect);
    OS.gdk_region_destroy(clipRegion);
    if (OS.pango_context_get_base_dir(context) == OS.PANGO_DIRECTION_RTL) {
      rect.x = (width() - rect.x) - rect.width;
    }
    return new Rectangle(rect.x, rect.y, rect.width, rect.height);
  }
}
