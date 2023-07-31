class PlaceHold {
  public void test_toString() {
    AccessibleTextEvent event = new AccessibleTextEvent(shell.getAccessible());
    assertNotNull(event.toString());
    assertTrue(event.toString().length() > 0);
  }
}
