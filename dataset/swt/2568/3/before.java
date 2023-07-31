class PlaceHold {
  void redrawWidget(
      int x, int y, int width, int height, boolean redrawAll, boolean all, boolean trim) {
    if (!gtk_widget_get_realized(handle)) {
      return;
    }
    int window = paintWindow();
    GdkRectangle rect = new GdkRectangle();
    if (redrawAll) {
      int[] w = new int[1];
      int[] h = new int[1];
      OS.gdk_drawable_get_size(window, w, h);
      rect.width = w[0];
      rect.height = h[0];
    } else {
      rect.x = x;
      rect.y = y;
      rect.width = width;
      rect.height = height;
    }
    OS.gdk_window_invalidate_rect(window, rect, all);
  }
}
