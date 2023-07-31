class PlaceHold {
  @Test
  public void testDouble() {
    buildRule.executeTarget("double");
    assertEquals("/abc/a:/abc/b", buildRule.getLog());
  }
}
