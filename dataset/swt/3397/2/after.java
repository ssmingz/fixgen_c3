class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(handle, allocation);
    int x = allocation.x;
    int y = allocation.y;
    int width = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
    int height = ((state & ZERO_HEIGHT) != 0) ? 0 : allocation.height;
    if ((parent.style & SWT.MIRRORED) != 0) {
      x = (parent.getClientWidth() - width) - x;
    }
    return new Rectangle(x, y, width, height);
  }
}
