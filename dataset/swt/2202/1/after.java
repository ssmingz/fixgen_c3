class PlaceHold {
  Rectangle getBounds() {
    checkWidget();
    if (!gtk_widget_get_mapped(handle)) {
      return new Rectangle(0, 0, 0, 0);
    }
    long window = gtk_widget_get_window(handle);
    int[] origin_x = new int[1];
    int[] origin_y = new int[1];
    OS.gdk_window_get_origin(window, origin_x, origin_y);
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(handle, allocation);
    int x = origin_x[0] + allocation.x;
    int y = origin_x[0] + allocation.y;
    int width = allocation.width;
    int height = allocation.height;
    return new Rectangle(x, y, width, height);
  }
}
