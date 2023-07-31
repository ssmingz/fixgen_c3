class PlaceHold {
  public void test_timerExecILjava_lang_Runnable() {
    final Display display = new Display();
    try {
      final boolean[] timerExecRan = new boolean[] {false};
      final boolean[] threadRan = new boolean[] {false};
      try {
        display.timerExec(0, null);
        fail("No exception thrown for timerExec with null runnable");
      } catch (IllegalArgumentException e) {
        assertSWTProblem(
            "Incorrect exception thrown for timerExec with null runnable", ERROR_NULL_ARGUMENT, e);
      }
      display.timerExec(
          -100,
          new Runnable() {
            public void run() {
              timerExecRan[0] = true;
            }
          });
      final int delay = 3000;
      final long startTime = System.currentTimeMillis();
      display.timerExec(
          delay,
          new Runnable() {
            public void run() {
              long endTime = System.currentTimeMillis();
              if (endTime < (startTime + delay)) {
                System.out.println(
                    "Display.timerExec ran early " + ((startTime + delay) - endTime));
              }
              threadRan[0] = true;
            }
          });
      while (!threadRan[0]) {
        if (!display.readAndDispatch()) {
          display.sleep();
        }
      }
      assertFalse("< 0 ms timer did execute", timerExecRan[0]);
    } finally {
      display.dispose();
    }
  }
}
