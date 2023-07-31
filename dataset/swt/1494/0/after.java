class PlaceHold {
  int gtk_button_release_event(int widget, int eventPtr) {
    int result = super.gtk_button_release_event(widget, eventPtr);
    if (result != 0) {
      return result;
    }
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, eventPtr, sizeof);
    int button = gdkEvent.button;
    if (button != 1) {
      return 0;
    }
    if (!dragging) {
      return 0;
    }
    dragging = false;
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(handle, allocation);
    int width = allocation.width;
    int height = allocation.height;
    Event event = new Event();
    event.time = gdkEvent.time;
    event.x = lastX;
    event.y = lastY;
    event.width = width;
    event.height = height;
    drawBand(lastX, lastY, width, height);
    if ((parent.style & SWT.MIRRORED) != 0) {
      event.x = (parent.getClientWidth() - width) - event.x;
    }
    sendSelectionEvent(Selection, event, true);
    if (isDisposed()) {
      return result;
    }
    if (event.doit) {
      if ((style & SWT.SMOOTH) != 0) {
        setBounds(event.x, event.y, width, height);
      }
    }
    return result;
  }
}
