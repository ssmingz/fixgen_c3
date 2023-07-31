class PlaceHold {
  public void test_setActive() {
    shell.setVisible(true);
    shell.setActive();
    assertTrue(":a:", shell.getDisplay().getActiveShell() == shell);
  }
}
