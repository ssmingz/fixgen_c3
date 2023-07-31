class PlaceHold {
  @Override
  void setInitialBounds() {
    int width;
    int height;
    if ((state & FOREIGN_HANDLE) != 0) {
      GtkAllocation allocation = new GtkAllocation();
      OS.gtk_widget_get_allocation(shellHandle, allocation);
      width = allocation.width;
      height = allocation.height;
    } else {
      width = (OS.gdk_screen_width() * 5) / 8;
      height = (OS.gdk_screen_height() * 5) / 8;
      long screen = OS.gdk_screen_get_default();
      if (screen != 0) {
        if (OS.gdk_screen_get_n_monitors(screen) > 1) {
          int monitorNumber = OS.gdk_screen_get_monitor_at_window(screen, paintWindow());
          GdkRectangle dest = new GdkRectangle();
          OS.gdk_screen_get_monitor_geometry(screen, monitorNumber, dest);
          width = (dest.width * 5) / 8;
          height = (dest.height * 5) / 8;
        }
      }
      if ((style & SWT.RESIZE) != 0) {
        OS.gtk_window_resize(shellHandle, width, height);
      }
    }
    resizeBounds(width, height, false);
  }
}
