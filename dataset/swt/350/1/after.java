class PlaceHold {
  int processMouseDown(int callData) {
    Shell shell = parent.getShell();
    XButtonEvent xEvent = new XButtonEvent();
    OS.memmove(xEvent, callData, sizeof);
    if (getGrabberArea().contains(xEvent.x, xEvent.y)) {
      dragging = true;
      mouseXOffset = xEvent.x;
      parent.setCursor(parent.dragCursor);
    }
    if (!shell.isDisposed()) {
      shell.setActiveControl(parent);
    }
    return 0;
  }
}
