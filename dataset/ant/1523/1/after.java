class PlaceHold {
  @Test
  public void testNestedC() {
    buildRule.executeTarget("nested.c");
    AntAssert.assertContains("add C called", buildRule.getLog());
  }
}
