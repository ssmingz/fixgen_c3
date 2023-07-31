class PlaceHold {
  int gtk_motion_notify_event(int widget, int event) {
    int result = super.gtk_motion_notify_event(widget, event);
    if (result != 0) {
      return result;
    }
    GdkEventMotion gdkEvent = new GdkEventMotion();
    OS.memmove(gdkEvent, event, sizeof);
    int x = ((int) (gdkEvent.x));
    int y = ((int) (gdkEvent.y));
    if ((style & SWT.MIRRORED) != 0) {
      x = getClientWidth() - x;
    }
    if ((gdkEvent.state & OS.GDK_BUTTON1_MASK) != 0) {
      int oldSelection = selection.y;
      selection.y = layout.getOffset(x, y, null);
      if (selection.y != oldSelection) {
        int newSelection = selection.y;
        if (oldSelection > newSelection) {
          int temp = oldSelection;
          oldSelection = newSelection;
          newSelection = temp;
        }
        Rectangle rect = layout.getBounds(oldSelection, newSelection);
        redraw(rect.x, rect.y, rect.width, rect.height, false);
      }
    } else {
      for (int j = 0; j < offsets.length; j++) {
        Rectangle[] rects = getRectangles(j);
        for (int i = 0; i < rects.length; i++) {
          Rectangle rect = rects[i];
          if (rect.contains(x, y)) {
            setCursor(display.getSystemCursor(CURSOR_HAND));
            return result;
          }
        }
      }
      setCursor(null);
    }
    return result;
  }
}
