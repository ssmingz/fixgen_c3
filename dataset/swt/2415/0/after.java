class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    parent.forceResize();
    int topHandle = topHandle();
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(topHandle, allocation);
    int x = allocation.x;
    int y = allocation.y;
    int width = allocation.width;
    int height = allocation.height;
    if ((parent.style & SWT.MIRRORED) != 0) {
      x = (parent.getClientWidth() - width) - x;
    }
    if (((style & SWT.SEPARATOR) != 0) && (control != null)) {
      height = Math.max(height, 23);
    }
    return new Rectangle(x, y, width, height);
  }
}
