class PlaceHold {
  public void test_ConstructorLjava_lang_Object() {
    AccessibleTextEvent event = new AccessibleTextEvent(shell.getAccessible());
    assertNotNull(event);
    event = new AccessibleTextEvent(new Integer(5));
    assertNotNull(event);
  }
}
