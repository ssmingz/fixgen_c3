class PlaceHold {
  boolean mouseEvent(int id, int sel, int theEvent, int type) {
    if (!display.sendEvent) {
      return true;
    }
    display.sendEvent = false;
    if (!isEventView(id)) {
      return true;
    }
    boolean dragging = false;
    boolean[] consume = null;
    NSEvent nsEvent = new NSEvent(theEvent);
    int nsType = ((int) (nsEvent.type()));
    NSInputManager manager = NSInputManager.currentInputManager();
    if ((manager != null) && manager.wantsToHandleMouseEvents()) {
      if (manager.handleMouseEvent(nsEvent)) {
        return true;
      }
    }
    switch (nsType) {
      case OS.NSLeftMouseDown:
        if (((nsEvent.clickCount() == 1) && ((state & DRAG_DETECT) != 0)) && hooks(DragDetect)) {
          consume = new boolean[1];
          NSPoint location = view.convertPoint_fromView_(nsEvent.locationInWindow(), null);
          dragging = dragDetect(((int) (location.x)), ((int) (location.y)), false, consume);
        }
        break;
      case OS.NSLeftMouseDragged:
      case OS.NSRightMouseDragged:
      case OS.NSOtherMouseDragged:
        display.checkEnterExit(this, nsEvent, false);
        break;
      case OS.NSLeftMouseUp:
      case OS.NSRightMouseUp:
      case OS.NSOtherMouseUp:
        display.checkEnterExit(display.findControl(true), nsEvent, false);
        break;
    }
    sendMouseEvent(nsEvent, type, false);
    if ((type == SWT.MouseDown) && (nsEvent.clickCount() == 2)) {
      sendMouseEvent(nsEvent, MouseDoubleClick, false);
    }
    if (dragging) {
      sendMouseEvent(nsEvent, DragDetect, false);
    }
    if ((consume != null) && consume[0]) {
      return false;
    }
    return true;
  }
}
