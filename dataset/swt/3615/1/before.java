class PlaceHold {
  public void test_ConstructorLjava_lang_Object() {
    AccessibleControlEvent event = new AccessibleControlEvent(shell);
    assertNotNull(event);
    event = new AccessibleControlEvent(new Integer(5));
    assertNotNull(event);
  }
}
