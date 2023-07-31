class PlaceHold {
  public void test_toString() {
    AccessibleEvent event = new AccessibleEvent(shell);
    assertNotNull(event.toString());
    assertTrue(event.toString().length() > 0);
  }
}
