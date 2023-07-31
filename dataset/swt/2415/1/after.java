class PlaceHold {
  int gtk_button_press_event(int widget, int eventPtr) {
    int result = super.gtk_button_press_event(widget, eventPtr);
    if (result != 0) {
      return result;
    }
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, eventPtr, sizeof);
    int button = gdkEvent.button;
    if (button != 1) {
      return 0;
    }
    if (gdkEvent.type == OS.GDK_2BUTTON_PRESS) {
      return 0;
    }
    if (gdkEvent.type == OS.GDK_3BUTTON_PRESS) {
      return 0;
    }
    int window = gtk_widget_get_window(widget);
    int[] origin_x = new int[1];
    int[] origin_y = new int[1];
    OS.gdk_window_get_origin(window, origin_x, origin_y);
    startX = ((int) (gdkEvent.x_root - origin_x[0]));
    startY = ((int) (gdkEvent.y_root - origin_y[0]));
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(handle, allocation);
    int x = allocation.x;
    int y = allocation.y;
    int width = allocation.width;
    int height = allocation.height;
    lastX = x;
    lastY = y;
    Event event = new Event();
    event.time = gdkEvent.time;
    event.x = lastX;
    event.y = lastY;
    event.width = width;
    event.height = height;
    if ((style & SWT.SMOOTH) == 0) {
      event.detail = SWT.DRAG;
    }
    if ((parent.style & SWT.MIRRORED) != 0) {
      event.x = (parent.getClientWidth() - width) - event.x;
    }
    sendSelectionEvent(Selection, event, true);
    if (isDisposed()) {
      return 0;
    }
    if (event.doit) {
      dragging = true;
      lastX = event.x;
      lastY = event.y;
      if ((parent.style & SWT.MIRRORED) != 0) {
        lastX = (parent.getClientWidth() - width) - lastX;
      }
      parent.update(true, (style & SWT.SMOOTH) == 0);
      drawBand(lastX, event.y, width, height);
      if ((style & SWT.SMOOTH) != 0) {
        setBounds(event.x, event.y, width, height);
      }
    }
    return result;
  }
}
