class PlaceHold {
  public Point getLocation() {
    checkWidget();
    int topHandle = topHandle();
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(topHandle, allocation);
    int x = allocation.x;
    int y = allocation.y;
    if ((parent.style & SWT.MIRRORED) != 0) {
      int width = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
      x = (parent.getClientWidth() - width) - x;
    }
    return new Point(x, y);
  }
}
