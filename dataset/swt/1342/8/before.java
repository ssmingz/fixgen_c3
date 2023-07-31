class PlaceHold {
  public Point getSize() {
    checkWidget();
    int topHandle = topHandle();
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(topHandle, allocation);
    int width = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
    int height = ((state & ZERO_HEIGHT) != 0) ? 0 : allocation.height;
    return new Point(width, height);
  }
}
