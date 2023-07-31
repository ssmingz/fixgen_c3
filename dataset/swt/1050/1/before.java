class PlaceHold {
  public void test_addMenuListenerLorg_eclipse_swt_events_MenuListener() {
    listenerCalled = false;
    MenuListener menuListener =
        new MenuListener() {
          public void menuShown(MenuEvent e) {
            listenerCalled = true;
          }

          public void menuHidden(MenuEvent e) {
            listenerCalled = true;
          }
        };
    try {
      menu.addMenuListener(null);
      fail("No exception thrown for addMenuListener with null argument");
    } catch (IllegalArgumentException e) {
    }
    menu.addMenuListener(menuListener);
    menu.notifyListeners(Show, new Event());
    assertTrue(":a:", listenerCalled);
    listenerCalled = false;
    menu.notifyListeners(Hide, new Event());
    assertTrue(":b:", listenerCalled);
    try {
      menu.removeMenuListener(null);
      fail("No exception thrown for removeMenuListener with null argument");
    } catch (IllegalArgumentException e) {
    }
    menu.removeMenuListener(menuListener);
  }
}
