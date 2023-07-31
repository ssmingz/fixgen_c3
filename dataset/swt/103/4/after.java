class PlaceHold {
  public void test_setSynchronizerLorg_eclipse_swt_widgets_Synchronizer() {
    final Display display = new Display();
    final boolean[] asyncExecRan = new boolean[] {false};
    try {
      try {
        display.setSynchronizer(null);
        fail("No exception thrown for post with null argument");
      } catch (IllegalArgumentException e) {
        assertSWTProblem(
            "Incorrect exception thrown for set synchronizer with null argument",
            ERROR_NULL_ARGUMENT,
            e);
      }
      class MySynchronizer extends Synchronizer {
        boolean invoked = false;

        MySynchronizer(Display d) {
          super(d);
        }

        @Override
        protected void asyncExec(Runnable runnable) {
          invoked = true;
          super.asyncExec(runnable);
        }
      }
      MySynchronizer mySynchronizer = new MySynchronizer(display);
      display.setSynchronizer(mySynchronizer);
      display.asyncExec(
          new Runnable() {
            public void run() {
              asyncExecRan[0] = true;
            }
          });
      while (display.readAndDispatch()) {}
      assertTrue(mySynchronizer.invoked);
      assertTrue(asyncExecRan[0]);
    } finally {
      display.dispose();
    }
  }
}
