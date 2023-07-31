class PlaceHold {
  public void test_getActiveShell() {
    Display display = new Display();
    try {
      Shell shell = new Shell(display);
      shell.open();
      assertTrue(display.getActiveShell() == shell);
      shell.dispose();
    } finally {
      display.dispose();
    }
  }
}
