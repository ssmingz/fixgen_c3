class PlaceHold {
  public void test_toString() {
    AccessibleControlEvent event = new AccessibleControlEvent(shell);
    assertNotNull(event.toString());
    assertTrue(event.toString().length() > 0);
  }
}
