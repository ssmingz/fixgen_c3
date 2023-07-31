class PlaceHold {
  public void test_addListenerILorg_eclipse_swt_widgets_Listener() {
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
        display.addListener(Close, null);
        fail("No exception thrown for addListener with null argument");
      } catch (IllegalArgumentException e) {
        assertSWTProblem(
            "Incorrect exception thrown for addListener with null argument",
            ERROR_NULL_ARGUMENT,
            e);
      }
      display.addListener(Dispose, listener);
    } finally {
      display.close();
    }
    assertFalse(":a:", callbackReceived[CLOSE_CALLBACK]);
    assertTrue(":b:", callbackReceived[DISPOSE_CALLBACK]);
    display = new Display();
    try {
      display.addListener(Close, listener);
    } finally {
      display.close();
    }
    assertTrue(":c:", callbackReceived[CLOSE_CALLBACK]);
  }
}
