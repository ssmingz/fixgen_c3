class PlaceHold {
  int gtk_key_press_event(int widget, int eventPtr) {
    if (!hasFocus()) {
      return 0;
    }
    int result = super.gtk_key_press_event(widget, eventPtr);
    if (result != 0) {
      return result;
    }
    ToolItem[] items = getItems();
    int length = items.length;
    int index = 0;
    while (index < length) {
      if (items[index].hasFocus()) {
        break;
      }
      index++;
    }
    GdkEventKey gdkEvent = new GdkEventKey();
    OS.memmove(gdkEvent, eventPtr, sizeof);
    boolean next = false;
    switch (gdkEvent.keyval) {
      case OS.GDK_Up:
      case OS.GDK_Left:
        next = false;
        break;
      case OS.GDK_Down:
        {
          if ((0 <= index) && (index < length)) {
            ToolItem item = items[index];
            if ((item.style & SWT.DROP_DOWN) != 0) {
              Event event = new Event();
              event.detail = SWT.ARROW;
              int topHandle = item.topHandle();
              event.x = OS.GTK_WIDGET_X(topHandle);
              event.y = OS.GTK_WIDGET_Y(topHandle) + OS.GTK_WIDGET_HEIGHT(topHandle);
              if ((style & SWT.MIRRORED) != 0) {
                event.x = (getClientWidth() - OS.GTK_WIDGET_WIDTH(topHandle)) - event.x;
              }
              item.postEvent(Selection, event);
              return result;
            }
          }
        }
      case OS.GDK_Right:
        next = true;
        break;
      default:
        return result;
    }
    if ((style & SWT.MIRRORED) != 0) {
      next = !next;
    }
    int start = index;
    int offset = (next) ? 1 : -1;
    while ((index = ((index + offset) + length) % length) != start) {
      ToolItem item = items[index];
      if (item.setFocus()) {
        return result;
      }
    }
    return result;
  }
}
