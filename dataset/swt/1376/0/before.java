class PlaceHold {
  public void test_consistency_Open() {
    if (fTestConsistency) {
      createShell();
      final Display display = shell.getDisplay();
      Vector events = new Vector();
      String[] temp = hookExpectedEvents(testShell, getTestName(), events);
      shell.pack();
      shell.open();
      testShell.pack();
      testShell.open();
      new Thread() {
        public void run() {
          display.asyncExec(
              new Thread() {
                public void run() {
                  shell.dispose();
                }
              });
        }
      }.start();
      while (!shell.isDisposed()) {
        if (!display.readAndDispatch()) {
          display.sleep();
        }
      }
      setUp();
      String[] results = new String[events.size()];
      events.copyInto(results);
      assertEquals(getTestName() + " event ordering", temp, results);
    }
  }
}
