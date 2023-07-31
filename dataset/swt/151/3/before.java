class PlaceHold {
  @Override
  public Rectangle computeTrim(int x, int y, int width, int height) {
    checkWidget();
    forceResize();
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(clientHandle, allocation);
    int clientX = allocation.x;
    int clientY = allocation.y;
    x -= clientX;
    y -= clientY;
    width += clientX + clientX;
    height += clientX + clientY;
    return new Rectangle(x, y, width, height);
  }
}
