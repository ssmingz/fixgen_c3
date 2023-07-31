class PlaceHold {
  public void test_ConstructorLjava_lang_Object() {
    AccessibleEvent event = new AccessibleEvent(shell.getAccessible());
    assertNotNull(event);
    event = new AccessibleEvent(new Integer(5));
    assertNotNull(event);
  }
}
