class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    int topHandle = topHandle();
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    GtkAllocation allocation = new GtkAllocation();
    if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
      OS.gtk_widget_get_allocation(topHandle, allocation);
      x = allocation.x;
      y = allocation.y;
      width = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
      height = ((state & ZERO_HEIGHT) != 0) ? 0 : allocation.height;
    } else {
      x = OS.GTK_WIDGET_X(topHandle);
      y = OS.GTK_WIDGET_Y(topHandle);
      width = ((state & ZERO_WIDTH) != 0) ? 0 : OS.GTK_WIDGET_WIDTH(topHandle);
      height = ((state & ZERO_HEIGHT) != 0) ? 0 : OS.GTK_WIDGET_HEIGHT(topHandle);
    }
    if ((parent.style & SWT.MIRRORED) != 0) {
      x = (parent.getClientWidth() - width) - x;
    }
    return new Rectangle(x, y, width, height);
  }
}
