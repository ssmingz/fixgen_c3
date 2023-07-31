class PlaceHold {
  int menuForEvent(int id, int sel, int theEvent) {
    if (!isEnabled()) {
      return 0;
    }
    NSPoint pt = NSEvent.mouseLocation();
    pt.y = ((int) (display.getPrimaryFrame().height - pt.y));
    int x = ((int) (pt.x));
    int y = ((int) (pt.y));
    Event event = new Event();
    event.x = x;
    event.y = y;
    sendEvent(MenuDetect, event);
    if (!event.doit) {
      return 0;
    }
    Menu menu = getMenu();
    if ((menu != null) && (!menu.isDisposed())) {
      if ((x != event.x) || (y != event.y)) {
        menu.setLocation(event.x, event.y);
      }
      return menu.nsMenu.id;
    }
    return super.menuForEvent(id, sel, theEvent);
  }
}
