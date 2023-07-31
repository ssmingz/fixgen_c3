class PlaceHold {
  void drawWithCairo(
      GC gc, int x, int y, int start, int end, boolean fullSelection, GdkColor fg, GdkColor bg) {
    GCData data = gc.data;
    long cairo = data.cairo;
    Cairo.cairo_save(cairo);
    if (!fullSelection) {
      Cairo.cairo_move_to(cairo, x, y);
      OS.pango_cairo_show_layout(cairo, layout);
      drawBorder(gc, x, y, null);
    }
    int[] ranges = new int[] {start, end};
    long rgn = OS.gdk_pango_layout_get_clip_region(layout, x, y, ranges, ranges.length / 2);
    if (rgn != 0) {
      OS.gdk_cairo_region(cairo, rgn);
      Cairo.cairo_clip(cairo);
      Cairo.cairo_set_source_rgba(
          cairo,
          (bg.red & 0xffff) / ((float) (0xffff)),
          (bg.green & 0xffff) / ((float) (0xffff)),
          (bg.blue & 0xffff) / ((float) (0xffff)),
          data.alpha / ((float) (0xff)));
      Cairo.cairo_paint(cairo);
      Region.cairo_region_destroy(rgn);
    }
    Cairo.cairo_set_source_rgba(
        cairo,
        (fg.red & 0xffff) / ((float) (0xffff)),
        (fg.green & 0xffff) / ((float) (0xffff)),
        (fg.blue & 0xffff) / ((float) (0xffff)),
        data.alpha / ((float) (0xff)));
    Cairo.cairo_move_to(cairo, x, y);
    OS.pango_layout_set_attributes(layout, selAttrList);
    OS.pango_cairo_show_layout(cairo, layout);
    OS.pango_layout_set_attributes(layout, attrList);
    drawBorder(gc, x, y, fg);
    Cairo.cairo_restore(cairo);
  }
}
