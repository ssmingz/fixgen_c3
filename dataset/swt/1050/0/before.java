class PlaceHold {
  public void test_addHelpListenerLorg_eclipse_swt_events_HelpListener() {
    listenerCalled = false;
    HelpListener listener =
        new HelpListener() {
          public void helpRequested(HelpEvent e) {
            listenerCalled = true;
          }
        };
    try {
      menu.addHelpListener(null);
      fail("No exception thrown for addHelpListener with null argument");
    } catch (IllegalArgumentException e) {
    }
    menu.addHelpListener(listener);
    menu.notifyListeners(Help, new Event());
    assertTrue(listenerCalled);
    try {
      menu.removeHelpListener(null);
      fail("No exception thrown for removeHelpListener with null argument");
    } catch (IllegalArgumentException e) {
    }
    menu.removeHelpListener(listener);
  }
}
