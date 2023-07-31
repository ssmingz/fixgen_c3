class PlaceHold {
  public void test_removeListenerILorg_eclipse_swt_widgets_Listener() {
    final int CLOSE_CALLBACK = 0;
    final int DISPOSE_CALLBACK = 1;
    final boolean[] callbackReceived = new boolean[] {false, false};
    Listener listener =
        new Listener() {
          public void handleEvent(Event e) {
            if (e.type == SWT.Close) {
              callbackReceived[CLOSE_CALLBACK] = true;
            } else if (e.type == SWT.Dispose) {
              callbackReceived[DISPOSE_CALLBACK] = true;
            }
          }
        };
    Display display = new Display();
    try {
      try {
        display.removeListener(Close, null);
        fail("No exception thrown for removeListener with null argument");
      } catch (IllegalArgumentException e) {
        assertEquals(
            "Incorrect exception thrown for removeListener with null argument",
            ERROR_NULL_ARGUMENT,
            e);
      }
      display.addListener(Dispose, listener);
      display.removeListener(Dispose, listener);
    } finally {
      display.close();
    }
    assertFalse(callbackReceived[CLOSE_CALLBACK]);
    assertFalse(callbackReceived[DISPOSE_CALLBACK]);
  }
}
