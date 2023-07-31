class PlaceHold {
  @Override
  long gtk_button_press_event(long widget, long event) {
    if (widget == shellHandle) {
      if (isCustomResize()) {
        if (((style & SWT.ON_TOP) != 0) && ((style & SWT.NO_FOCUS) == 0)) {
          forceActive();
        }
        GdkEventButton gdkEvent = new GdkEventButton();
        OS.memmove(gdkEvent, event, sizeof);
        if (gdkEvent.button == 1) {
          display.resizeLocationX = gdkEvent.x_root;
          display.resizeLocationY = gdkEvent.y_root;
          int[] x = new int[1];
          int[] y = new int[1];
          OS.gtk_window_get_position(shellHandle, x, y);
          display.resizeBoundsX = x[0];
          display.resizeBoundsY = y[0];
          GtkAllocation allocation = new GtkAllocation();
          OS.gtk_widget_get_allocation(shellHandle, allocation);
          display.resizeBoundsWidth = allocation.width;
          display.resizeBoundsHeight = allocation.height;
        }
      }
      return 0;
    }
    return super.gtk_button_press_event(widget, event);
  }
}
