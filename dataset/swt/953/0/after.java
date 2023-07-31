class PlaceHold {
  public void test_setActive() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_setActive(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Shell))");
      }
      return;
    }
    Shell shell2 = new Shell();
    shell2.open();
    shell.setVisible(true);
    shell.setActive();
    assertTrue("visible shell was not made active", shell.getDisplay().getActiveShell() == shell);
    shell2.setActive();
    testShell.setVisible(true);
    testShell.setActive();
    assertTrue(
        "visible dialog shell was not made active",
        testShell.getDisplay().getActiveShell() == testShell);
    shell2.setActive();
    shell.setVisible(false);
    shell.setActive();
    assertTrue("non-visible shell was made active", shell.getDisplay().getActiveShell() != shell);
    shell2.setActive();
    testShell.setVisible(false);
    testShell.setActive();
    assertTrue(
        "non-visible dialog shell was made active",
        testShell.getDisplay().getActiveShell() != testShell);
    shell2.dispose();
  }
}
