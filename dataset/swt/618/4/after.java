class PlaceHold {
  void onMouseMove(Event event) {
    if (isLocked) {
      return;
    }
    CoolItem grabbed = getGrabbedItem(event.x, event.y);
    if (dragging != null) {
      int left_root = toDisplay(new Point(event.x, event.y)).x - itemXOffset;
      Rectangle bounds = dragging.getBounds();
      if (event.y < bounds.y) {
        moveUp(dragging, left_root);
      } else if (event.y > (bounds.y + bounds.height)) {
        moveDown(dragging, left_root);
      } else if (event.x < mouseXOffset) {
        int distance = Math.min(mouseXOffset, bounds.x + itemXOffset) - event.x;
        if (distance > 0) {
          moveLeft(dragging, distance);
        }
      } else if (event.x > mouseXOffset) {
        int distance = event.x - Math.max(mouseXOffset, bounds.x + itemXOffset);
        if (distance > 0) {
          moveRight(dragging, distance);
        }
      }
      mouseXOffset = event.x;
      return;
    }
    if (grabbed != null) {
      setCursor(hoverCursor);
    } else {
      setCursor(null);
    }
  }
}
