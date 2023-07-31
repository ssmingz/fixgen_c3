class PlaceHold {
  public Rectangle getClientArea() {
    checkWidget();
    forceResize();
    int clientHandle = clientHandle();
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    GtkAllocation allocation = new GtkAllocation();
    if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
      OS.gtk_widget_get_allocation(clientHandle, allocation);
      x = allocation.x;
      y = allocation.y;
      width = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
      height = ((state & ZERO_HEIGHT) != 0) ? 0 : allocation.height;
    } else {
      x = OS.GTK_WIDGET_X(clientHandle);
      y = OS.GTK_WIDGET_Y(clientHandle);
      width = ((state & ZERO_WIDTH) != 0) ? 0 : OS.GTK_WIDGET_WIDTH(clientHandle);
      height = ((state & ZERO_HEIGHT) != 0) ? 0 : OS.GTK_WIDGET_HEIGHT(clientHandle);
    }
    return new Rectangle(x, y, width, height);
  }
}
