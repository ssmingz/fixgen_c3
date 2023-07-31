class PlaceHold {
  @Test
  public void testConditionConditionType() {
    buildRule.executeTarget("condition.condition.type");
    AntAssert.assertContains("My Condition eval", buildRule.getLog());
  }
}
