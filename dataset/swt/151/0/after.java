class PlaceHold {
  public Rectangle getClientArea() {
    checkWidget();
    forceResize();
    long clientHandle = clientHandle();
    GtkAllocation allocation = new GtkAllocation();
    OS.gtk_widget_get_allocation(clientHandle, allocation);
    int x = allocation.x;
    int y = allocation.y;
    int width = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
    int height = ((state & ZERO_HEIGHT) != 0) ? 0 : allocation.height;
    return new Rectangle(x, y, width, height);
  }
}
