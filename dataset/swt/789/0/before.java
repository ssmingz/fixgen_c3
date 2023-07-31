class PlaceHold {
  boolean showMenu(int x, int y) {
    Event event = new Event();
    event.x = x;
    event.y = y;
    sendEvent(MenuDetect, event);
    if (isDisposed()) {
      return false;
    }
    if (!event.doit) {
      return true;
    }
    Menu menu = getMenu();
    if ((menu != null) && (!menu.isDisposed())) {
      if ((x != event.x) || (y != event.y)) {
        menu.setLocation(event.x, event.y);
      }
      menu.setVisible(true);
      return true;
    }
    return false;
  }
}
