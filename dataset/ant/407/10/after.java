class PlaceHold {
  @Test
  public void testNestedA() {
    buildRule.executeTarget("nested.a");
    AntAssert.assertContains("add A called", buildRule.getLog());
  }
}
