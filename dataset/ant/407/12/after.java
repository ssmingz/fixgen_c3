class PlaceHold {
  @Test
  public void testConditionType() {
    buildRule.executeTarget("condition.type");
    AntAssert.assertContains("beforeafter", buildRule.getLog());
  }
}
