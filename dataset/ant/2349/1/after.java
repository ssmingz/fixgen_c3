class PlaceHold {
  @Test
  public void testConditionTask() {
    buildRule.executeTarget("condition.task");
    AntAssert.assertContains("My Condition execution", buildRule.getLog());
  }
}
