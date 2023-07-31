class PlaceHold {
  public void test_setActive() {
    shell.setActive();
    assertTrue(":a:", shell.getDisplay().getActiveShell() == shell);
  }
}
