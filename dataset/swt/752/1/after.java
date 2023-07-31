class PlaceHold {
  int processMouseEnter(MacMouseEvent mme) {
    Event event = new Event();
    Point p = MacUtil.toControl(handle, mme.getWhere());
    event.x = p.x;
    event.y = p.y;
    postEvent(MouseEnter, event);
    return 0;
  }
}
