class PlaceHold {
  @Test
  public void testConditionTask() {
    buildRule.executeTarget("testConditionTask");
    TaskAdapter ta = ((TaskAdapter) (buildRule.getProject().getReference("cond")));
    ConditionTask c = ((ConditionTask) (ta.getProxy()));
    assertFalse(c.getLocation() == Location.UNKNOWN_LOCATION);
    assertFalse(c.getLocation().getLineNumber() == 0);
  }
}
