class PlaceHold {
  @Override
  public Rectangle getClientArea() {
    checkWidget();
    if ((state & CANVAS) != 0) {
      if (((state & ZERO_WIDTH) != 0) && ((state & ZERO_HEIGHT) != 0)) {
        return new Rectangle(0, 0, 0, 0);
      }
      forceResize();
      long clientHandle = clientHandle();
      GtkAllocation allocation = new GtkAllocation();
      gtk_widget_get_allocation(clientHandle, allocation);
      int width = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
      int height = ((state & ZERO_HEIGHT) != 0) ? 0 : allocation.height;
      return new Rectangle(0, 0, width, height);
    }
    return super.getClientArea();
  }
}
