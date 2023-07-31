class PlaceHold {
  public void test_toString() {
    AccessibleTextEvent event = new AccessibleTextEvent(shell);
    assertNotNull(event.toString());
    assertTrue(event.toString().length() > 0);
  }
}
