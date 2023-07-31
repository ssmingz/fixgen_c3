class PlaceHold {
  @Override
  long gtk_size_allocate(long widget, long allocation) {
    GtkAllocation widgetAllocation = new GtkAllocation();
    OS.gtk_widget_get_allocation(shellHandle, widgetAllocation);
    int width = widgetAllocation.width;
    int height = widgetAllocation.height;
    if (((!resized) || (oldWidth != width)) || (oldHeight != height)) {
      oldWidth = width;
      oldHeight = height;
      resizeBounds(width, height, true);
    }
    return 0;
  }
}
