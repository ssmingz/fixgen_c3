class PlaceHold {
  @Test
  public void testNestedB() {
    buildRule.executeTarget("nested.b");
    AntAssert.assertContains("add B called", buildRule.getLog());
  }
}
