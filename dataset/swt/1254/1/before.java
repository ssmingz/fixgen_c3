class PlaceHold {
  long gtk_motion_notify_event(long widget, long eventPtr) {
    long result = super.gtk_motion_notify_event(widget, eventPtr);
    if (result != 0) {
      return result;
    }
    if (!dragging) {
      return 0;
    }
    GdkEventMotion gdkEvent = new GdkEventMotion();
    OS.memmove(gdkEvent, eventPtr, sizeof);
    int eventX;
    int eventY;
    int eventState;
    if (gdkEvent.is_hint != 0) {
      int[] pointer_x = new int[1];
      int[] pointer_y = new int[1];
      int[] mask = new int[1];
      OS.gdk_window_get_pointer(gdkEvent.window, pointer_x, pointer_y, mask);
      eventX = pointer_x[0];
      eventY = pointer_y[0];
      eventState = mask[0];
    } else {
      int[] origin_x = new int[1];
      int[] origin_y = new int[1];
      OS.gdk_window_get_origin(gdkEvent.window, origin_x, origin_y);
      eventX = ((int) (gdkEvent.x_root - origin_x[0]));
      eventY = ((int) (gdkEvent.y_root - origin_y[0]));
      eventState = gdkEvent.state;
    }
    if ((eventState & OS.GDK_BUTTON1_MASK) == 0) {
      return 0;
    }
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(handle, allocation);
    int x = allocation.x;
    int y = allocation.y;
    int width = allocation.width;
    int height = allocation.height;
    int parentBorder = 0;
    gtk_widget_get_allocation(handle, allocation);
    int parentWidth = allocation.width;
    int parentHeight = allocation.height;
    int newX = lastX;
    int newY = lastY;
    if ((style & SWT.VERTICAL) != 0) {
      newX = Math.min(Math.max(0, ((eventX + x) - startX) - parentBorder), parentWidth - width);
    } else {
      newY = Math.min(Math.max(0, ((eventY + y) - startY) - parentBorder), parentHeight - height);
    }
    if ((newX == lastX) && (newY == lastY)) {
      return 0;
    }
    drawBand(lastX, lastY, width, height);
    Event event = new Event();
    event.time = gdkEvent.time;
    event.x = newX;
    event.y = newY;
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
      lastX = event.x;
      lastY = event.y;
      if ((parent.style & SWT.MIRRORED) != 0) {
        lastX = (parent.getClientWidth() - width) - lastX;
      }
    }
    parent.update(true, (style & SWT.SMOOTH) == 0);
    drawBand(lastX, lastY, width, height);
    if ((style & SWT.SMOOTH) != 0) {
      setBounds(event.x, lastY, width, height);
    }
    return result;
  }
}
