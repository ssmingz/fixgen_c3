class PlaceHold {
  public void test_getActiveShell() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_getActiveShell(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Display)");
      }
      return;
    }
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
