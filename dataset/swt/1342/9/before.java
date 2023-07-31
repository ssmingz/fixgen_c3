class PlaceHold {
  void setInitialBounds() {
    if (((state & ZERO_WIDTH) != 0) && ((state & ZERO_HEIGHT) != 0)) {
      int topHandle = topHandle();
      GtkAllocation allocation = new GtkAllocation();
      if ((parent.style & SWT.MIRRORED) != 0) {
        if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
          allocation.x = parent.getClientWidth();
        } else {
          OS.GTK_WIDGET_SET_X(topHandle, parent.getClientWidth());
        }
      } else if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
        allocation.x = 0;
      } else {
        OS.GTK_WIDGET_SET_X(topHandle, 0);
      }
      if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
        allocation.y = 0;
        OS.gtk_widget_set_allocation(topHandle, allocation);
      } else {
        OS.GTK_WIDGET_SET_Y(topHandle, 0);
      }
    } else {
      resizeHandle(1, 1);
      forceResize();
    }
  }
}
