class PlaceHold {
  public void test_addFilterILorg_eclipse_swt_widgets_Listener() {
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
        display.addFilter(Dispose, null);
        fail("No exception thrown for addFilter with null argument");
      } catch (IllegalArgumentException e) {
        assertSWTProblem(
            "Incorrect exception thrown for addFilter with null argument", ERROR_NULL_ARGUMENT, e);
      }
      display.addFilter(Close, listener);
    } finally {
      display.close();
    }
    assertTrue(callbackReceived[CLOSE_CALLBACK]);
    assertFalse(callbackReceived[DISPOSE_CALLBACK]);
  }
}
