class PlaceHold {
  int gtk_button_press_event(int widget, int event) {
    int result = super.gtk_button_press_event(widget, event);
    if (result != 0) {
      return result;
    }
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, event, sizeof);
    if ((gdkEvent.button == 1) && (gdkEvent.type == OS.GDK_BUTTON_PRESS)) {
      if (focusIndex != (-1)) {
        setFocus();
      }
      int x = ((int) (gdkEvent.x));
      int y = ((int) (gdkEvent.y));
      int offset = layout.getOffset(x, y, null);
      int oldSelectionX = selection.x;
      int oldSelectionY = selection.y;
      selection.x = offset;
      selection.y = -1;
      if ((oldSelectionX != (-1)) && (oldSelectionY != (-1))) {
        if (oldSelectionX > oldSelectionY) {
          int temp = oldSelectionX;
          oldSelectionX = oldSelectionY;
          oldSelectionY = temp;
        }
        Rectangle rect = layout.getBounds(oldSelectionX, oldSelectionY);
        redraw(rect.x, rect.y, rect.width, rect.height, false);
      }
      for (int j = 0; j < offsets.length; j++) {
        Rectangle[] rects = getRectangles(j);
        for (int i = 0; i < rects.length; i++) {
          Rectangle rect = rects[i];
          if (rect.contains(x, y)) {
            focusIndex = j;
            redraw();
            return result;
          }
        }
      }
    }
    return result;
  }
}
