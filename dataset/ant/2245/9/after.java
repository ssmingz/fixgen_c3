class PlaceHold {
  @Test
  public void testNested() {
    buildRule.executeTarget("nested");
    assertEquals("/abc/a:/abc/b", buildRule.getLog());
  }
}
