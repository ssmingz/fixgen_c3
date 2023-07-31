class PlaceHold {
  long sizeAllocateProc(long handle, long arg0, long user_data) {
    int offset = 16;
    int[] x = new int[1];
    int[] y = new int[1];
    gdk_window_get_device_position(0, x, y, null);
    y[0] += offset;
    long screen = OS.gdk_screen_get_default();
    if (screen != 0) {
      int monitorNumber = OS.gdk_screen_get_monitor_at_point(screen, x[0], y[0]);
      GdkRectangle dest = new GdkRectangle();
      OS.gdk_screen_get_monitor_geometry(screen, monitorNumber, dest);
      GtkAllocation allocation = new GtkAllocation();
      gtk_widget_get_allocation(handle, allocation);
      int width = allocation.width;
      int height = allocation.height;
      if ((x[0] + width) > (dest.x + dest.width)) {
        x[0] = (dest.x + dest.width) - width;
      }
      if ((y[0] + height) > (dest.y + dest.height)) {
        y[0] = (dest.y + dest.height) - height;
      }
    }
    OS.gtk_window_move(handle, x[0], y[0]);
    return 0;
  }
}
