class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    parent.forceResize();
    int topHandle = topHandle();
    int x;
    int y;
    int width;
    int height;
    GtkAllocation allocation = new GtkAllocation();
    if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
      OS.gtk_widget_get_allocation(topHandle, allocation);
      x = allocation.x;
      y = allocation.y;
      width = allocation.width;
      height = allocation.height;
    } else {
      x = OS.GTK_WIDGET_X(topHandle);
      y = OS.GTK_WIDGET_Y(topHandle);
      width = OS.GTK_WIDGET_WIDTH(topHandle);
      height = OS.GTK_WIDGET_HEIGHT(topHandle);
    }
    if ((parent.style & SWT.MIRRORED) != 0) {
      x = (parent.getClientWidth() - width) - x;
    }
    if (((style & SWT.SEPARATOR) != 0) && (control != null)) {
      height = Math.max(height, 23);
    }
    return new Rectangle(x, y, width, height);
  }
}
