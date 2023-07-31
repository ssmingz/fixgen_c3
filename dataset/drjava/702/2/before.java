class PlaceHold {
  public void setUp() throws Exception {
    super.setUp();
    DrJava.getConfig().resetToDefaults();
    _frame = new MainFrame();
    _frame.pack();
  }
}
