class PlaceHold {
  @Test
  public void testFileset() {
    buildRule.executeTarget("testFileset");
    assertPropertyContains("testFileset", ".xml ");
    assertPropertyEndsWith("testFileset", ".xml");
  }
}
