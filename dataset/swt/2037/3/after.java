class PlaceHold {
  static void gdk_region_get_rectangles(long region, long[] rectangles, int[] n_rectangles) {
    if (OS.GTK_VERSION < OS.VERSION(3, 0, 0)) {
      OS.gdk_region_get_rectangles(region, rectangles, n_rectangles);
      return;
    }
    int num = Cairo.cairo_region_num_rectangles(region);
    rectangles[0] = OS.g_malloc(GdkRectangle.sizeof * num);
    for (int n = 0; n < num; n++) {
      Cairo.cairo_region_get_rectangle(region, n, rectangles[0] + (n * GdkRectangle.sizeof));
    }
  }
}
