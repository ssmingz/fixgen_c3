class PlaceHold {
  int gtk_key_press_event(int widget, int eventPtr) {
    int result = super.gtk_key_press_event(widget, eventPtr);
    if (result != 0) {
      return result;
    }
    GdkEventKey gdkEvent = new GdkEventKey();
    OS.memmove(gdkEvent, eventPtr, sizeof);
    int keyval = gdkEvent.keyval;
    switch (keyval) {
      case OS.GDK_Left:
      case OS.GDK_Right:
      case OS.GDK_Up:
      case OS.GDK_Down:
        int xChange = 0;
        int yChange = 0;
        int stepSize = PAGE_INCREMENT;
        if ((gdkEvent.state & OS.GDK_CONTROL_MASK) != 0) {
          stepSize = INCREMENT;
        }
        if ((style & SWT.VERTICAL) != 0) {
          if ((keyval == OS.GDK_Up) || (keyval == OS.GDK_Down)) {
            break;
          }
          xChange = (keyval == OS.GDK_Left) ? -stepSize : stepSize;
        } else {
          if ((keyval == OS.GDK_Left) || (keyval == OS.GDK_Right)) {
            break;
          }
          yChange = (keyval == OS.GDK_Up) ? -stepSize : stepSize;
        }
        int width = OS.GTK_WIDGET_WIDTH(handle);
        int height = OS.GTK_WIDGET_HEIGHT(handle);
        int parentBorder = 0;
        int parentWidth = OS.GTK_WIDGET_WIDTH(handle);
        int parentHeight = OS.GTK_WIDGET_HEIGHT(handle);
        int newX = lastX;
        int newY = lastY;
        if ((style & SWT.VERTICAL) != 0) {
          newX =
              Math.min(
                  Math.max(0, ((lastX + xChange) - parentBorder) - startX), parentWidth - width);
        } else {
          newY =
              Math.min(
                  Math.max(0, ((lastY + yChange) - parentBorder) - startY), parentHeight - height);
        }
        if ((newX == lastX) && (newY == lastY)) {
          return result;
        }
        int window = OS.GTK_WIDGET_WINDOW(handle);
        int grabMask = OS.GDK_POINTER_MOTION_MASK | OS.GDK_BUTTON_RELEASE_MASK;
        int gdkCursor = (cursor != null) ? cursor.handle : defaultCursor;
        int ptrGrabResult =
            OS.gdk_pointer_grab(window, false, grabMask, window, gdkCursor, GDK_CURRENT_TIME);
        Event event = new Event();
        event.time = gdkEvent.time;
        event.x = newX;
        event.y = newY;
        event.width = width;
        event.height = height;
        if ((parent.style & SWT.MIRRORED) != 0) {
          event.x = (parent.getClientWidth() - width) - event.x;
        }
        sendSelectionEvent(Selection, event, true);
        if (ptrGrabResult == OS.GDK_GRAB_SUCCESS) {
          OS.gdk_pointer_ungrab(GDK_CURRENT_TIME);
        }
        if (isDisposed()) {
          break;
        }
        if (event.doit) {
          lastX = event.x;
          lastY = event.y;
          if ((parent.style & SWT.MIRRORED) != 0) {
            lastX = (parent.getClientWidth() - width) - lastX;
          }
          if ((style & SWT.SMOOTH) != 0) {
            setBounds(event.x, event.y, width, height);
            if (isDisposed()) {
              break;
            }
          }
          int cursorX = event.x;
          int cursorY = event.y;
          if ((style & SWT.VERTICAL) != 0) {
            cursorY += height / 2;
          } else {
            cursorX += width / 2;
          }
          display.setCursorLocation(parent.toDisplay(cursorX, cursorY));
        }
        break;
    }
    return result;
  }
}
