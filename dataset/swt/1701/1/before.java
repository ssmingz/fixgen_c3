class PlaceHold {
  int processMouseDown(int callData) {
    XButtonEvent xEvent = new XButtonEvent();
    OS.memmove(xEvent, callData, sizeof);
    if (getGrabberArea().contains(xEvent.x, xEvent.y)) {
      dragging = true;
      mouseXOffset = xEvent.x;
      parent.setCursor(parent.dragCursor);
    }
    return 0;
  }
}
