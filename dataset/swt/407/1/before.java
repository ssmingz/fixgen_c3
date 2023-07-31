class PlaceHold {
  public Point getLocation() {
    checkWidget();
    int topHandle = topHandle();
    int x = 0;
    int y = 0;
    int width = 0;
    GtkAllocation allocation = new GtkAllocation();
    if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
      OS.gtk_widget_get_allocation(topHandle, allocation);
      x = allocation.x;
      y = allocation.y;
    } else {
      x = OS.GTK_WIDGET_X(topHandle);
      y = OS.GTK_WIDGET_Y(topHandle);
    }
    if ((parent.style & SWT.MIRRORED) != 0) {
      if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
        width = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
      } else {
        width = ((state & ZERO_WIDTH) != 0) ? 0 : OS.GTK_WIDGET_WIDTH(topHandle);
      }
      x = (parent.getClientWidth() - width) - x;
    }
    return new Point(x, y);
  }
}
