class PlaceHold {
  int processMouseExit(MacMouseEvent mme) {
    Display display = getDisplay();
    display.removeMouseHoverTimeOut();
    display.hideToolTip();
    Event event = new Event();
    Point p = MacUtil.toControl(handle, mme.getWhere());
    event.x = p.x;
    event.y = p.y;
    postEvent(MouseExit, event);
    return OS.noErr;
  }
}
