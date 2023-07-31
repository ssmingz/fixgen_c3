class PlaceHold {
  boolean sendMouseEvent(
      int type,
      short button,
      int count,
      int detail,
      boolean send,
      int chord,
      short x,
      short y,
      int modifiers) {
    boolean result =
        super.sendMouseEvent(type, button, count, detail, send, chord, x, y, modifiers);
    switch (type) {
      case SWT.MouseDown:
        if ((button == 1) && (count == 1)) {
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
            Rectangle rectangle = layout.getBounds(oldSelectionX, oldSelectionY);
            redraw(rectangle.x, rectangle.y, rectangle.width, rectangle.height, false);
          }
          for (int j = 0; j < offsets.length; j++) {
            Rectangle[] rects = getRectangles(j);
            for (int i = 0; i < rects.length; i++) {
              Rectangle rectangle = rects[i];
              if (rectangle.contains(x, y)) {
                focusIndex = j;
                redraw();
                setFocus();
                return result;
              }
            }
          }
        }
        break;
      case SWT.MouseMove:
        if ((chord & 0x1) != 0) {
          int oldSelection = selection.y;
          selection.y = layout.getOffset(x, y, null);
          if (selection.y != oldSelection) {
            int newSelection = selection.y;
            if (oldSelection > newSelection) {
              int temp = oldSelection;
              oldSelection = newSelection;
              newSelection = temp;
            }
            Rectangle rectangle = layout.getBounds(oldSelection, newSelection);
            redraw(rectangle.x, rectangle.y, rectangle.width, rectangle.height, false);
          }
        }
        break;
      case SWT.MouseUp:
        if (focusIndex == (-1)) {
          break;
        }
        if (button == 1) {
          Rectangle[] rects = getRectangles(focusIndex);
          for (int i = 0; i < rects.length; i++) {
            Rectangle rectangle = rects[i];
            if (rectangle.contains(x, y)) {
              Event event = new Event();
              event.text = ids[focusIndex];
              notifyListeners(Selection, event);
              return result;
            }
          }
        }
        break;
    }
    return result;
  }
}
