class PlaceHold {
  public void setUp() throws Exception {
    super.setUp();
    DrJava.getConfig().resetToDefaults();
    _frame = new MainFrame();
    Utilities.invokeAndWait(
        new Runnable() {
          public void run() {
            _frame.pack();
          }
        });
  }
}
