class PlaceHold {
  public void test_toString() {
    AccessibleEvent event = new AccessibleEvent(shell.getAccessible());
    assertNotNull(event.toString());
    assertTrue(event.toString().length() > 0);
  }
}
