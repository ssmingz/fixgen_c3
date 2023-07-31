class PlaceHold {
  void redrawWidget(
      int x, int y, int width, int height, boolean redrawAll, boolean all, boolean trim) {
    super.redrawWidget(x, y, width, height, redrawAll, all, trim);
    if (!OS.gtk_widget_get_realized(handle)) {
      return;
    }
    if (!trim) {
      return;
    }
    int topHandle = topHandle();
    int paintHandle = paintHandle();
    if (topHandle == paintHandle) {
      return;
    }
    int window = gtk_widget_get_window(topHandle);
    GdkRectangle rect = new GdkRectangle();
    if (redrawAll) {
      GtkAllocation allocation = new GtkAllocation();
      if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
        OS.gtk_widget_get_allocation(topHandle, allocation);
        rect.width = allocation.width;
        rect.height = allocation.height;
      } else {
        rect.width = OS.GTK_WIDGET_WIDTH(topHandle);
        rect.height = OS.GTK_WIDGET_HEIGHT(topHandle);
      }
    } else {
      int[] destX = new int[1];
      int[] destY = new int[1];
      OS.gtk_widget_translate_coordinates(paintHandle, topHandle, x, y, destX, destY);
      rect.x = destX[0];
      rect.y = destY[0];
      rect.width = width;
      rect.height = height;
    }
    OS.gdk_window_invalidate_rect(window, rect, all);
  }
}
