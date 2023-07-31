class PlaceHold {
  boolean showMenu(int x, int y) {
    Event event = new Event();
    event.x = x;
    event.y = y;
    sendEvent(MenuDetect, event);
    if (isDisposed()) {
      return false;
    }
    if (event.doit) {
      if ((menu != null) && (!menu.isDisposed())) {
        boolean hooksKeys = hooks(KeyDown) || hooks(KeyUp);
        menu.createIMMenu(hooksKeys ? imHandle() : 0);
        if ((event.x != x) || (event.y != y)) {
          menu.setLocation(event.x, event.y);
        }
        menu.setVisible(true);
        return true;
      }
    }
    return false;
  }
}
