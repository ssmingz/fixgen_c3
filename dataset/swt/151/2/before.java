class PlaceHold {
  @Override
  public Rectangle getClientArea() {
    checkWidget();
    forceResize();
    OS.gtk_widget_realize(handle);
    long fixedWindow = gtk_widget_get_window(fixedHandle);
    long binWindow = OS.gtk_tree_view_get_bin_window(handle);
    int[] binX = new int[1];
    int[] binY = new int[1];
    OS.gdk_window_get_origin(binWindow, binX, binY);
    int[] fixedX = new int[1];
    int[] fixedY = new int[1];
    OS.gdk_window_get_origin(fixedWindow, fixedX, fixedY);
    long clientHandle = clientHandle();
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(clientHandle, allocation);
    int width = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
    int height = ((state & ZERO_HEIGHT) != 0) ? 0 : allocation.height;
    return new Rectangle(fixedX[0] - binX[0], fixedY[0] - binY[0], width, height);
  }
}
