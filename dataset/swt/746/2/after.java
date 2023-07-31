class PlaceHold {
  public void test_toString() {
    AccessibleControlEvent event = new AccessibleControlEvent(shell.getAccessible());
    assertNotNull(event.toString());
    assertTrue(event.toString().length() > 0);
  }
}
