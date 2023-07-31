class PlaceHold {
  public void setUp() throws Exception {
    super.setUp();
    _log.log("super.setUp() for next test completed");
    _frame = new MainFrame();
    _log.log("new MainFrame() for next test completed");
    Utilities.invokeAndWait(
        new Runnable() {
          public void run() {
            _frame.pack();
          }
        });
    _log.log("setUp complete for next test");
  }
}
