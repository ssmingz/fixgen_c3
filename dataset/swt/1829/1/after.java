class PlaceHold {
  public Rectangle getThumbBounds() {
    checkWidget();
    int[] slider_start = new int[1];
    int[] slider_end = new int[1];
    gtk_range_get_slider_range(handle, slider_start, slider_end);
    int x;
    int y;
    int width;
    int height;
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(handle, allocation);
    if ((style & SWT.VERTICAL) != 0) {
      x = allocation.x;
      y = slider_start[0];
      width = allocation.width;
      height = slider_end[0] - slider_start[0];
    } else {
      x = slider_start[0];
      y = allocation.y;
      width = slider_end[0] - slider_start[0];
      height = allocation.height;
    }
    Rectangle rect = new Rectangle(x, y, width, height);
    int[] origin_x = new int[1];
    int[] origin_y = new int[1];
    long window = gtk_widget_get_window(parent.scrolledHandle);
    if (window != 0) {
      OS.gdk_window_get_origin(window, origin_x, origin_y);
    }
    rect.x += origin_x[0];
    rect.y += origin_y[0];
    window = gtk_widget_get_window(parent.handle);
    if (window != 0) {
      OS.gdk_window_get_origin(window, origin_x, origin_y);
    }
    rect.x -= origin_x[0];
    rect.y -= origin_y[0];
    return rect;
  }
}
