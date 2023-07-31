class PlaceHold {
  @Test
  public void testConditionConditionTask() {
    try {
      buildRule.executeTarget("condition.condition.task");
      fail("Build exception expected: Task masking condition");
    } catch (BuildException ex) {
      AntAssert.assertContains("doesn't support the nested", ex.getMessage());
    }
  }
}
