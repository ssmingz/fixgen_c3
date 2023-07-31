class PlaceHold {
  public void setUp() throws IOException {
    Utilities.invokeAndWait(
        new Runnable() {
          public void run() {
            _frame = new MainFrame();
          }
        });
    _frame.pack();
    super.setUp();
  }
}
