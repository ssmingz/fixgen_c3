class PlaceHold {
  int sizeAllocateProc(int handle, int arg0, int user_data) {
    int offset = 16;
    int[] x = new int[1];
    int[] y = new int[1];
    OS.gdk_window_get_pointer(0, x, y, null);
    y[0] += offset;
    int screen = OS.gdk_screen_get_default();
    if (screen != 0) {
      int monitorNumber = OS.gdk_screen_get_monitor_at_point(screen, x[0], y[0]);
      GdkRectangle dest = new GdkRectangle();
      OS.gdk_screen_get_monitor_geometry(screen, monitorNumber, dest);
      int width = 0;
      int height = 0;
      GtkAllocation allocation = new GtkAllocation();
      if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
        OS.gtk_widget_get_allocation(handle, allocation);
        width = allocation.width;
        height = allocation.height;
      } else {
        width = OS.GTK_WIDGET_WIDTH(handle);
        height = OS.GTK_WIDGET_HEIGHT(handle);
      }
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
