class PlaceHold {
  public void testConditionTask() {
    executeTarget("testConditionTask");
    TaskAdapter ta = ((TaskAdapter) (getProject().getReference("cond")));
    ConditionTask c = ((ConditionTask) (ta.getProxy()));
    assertFalse(c.getLocation() == Location.UNKNOWN_LOCATION);
    assertFalse(c.getLocation().getLineNumber() == 0);
  }
}
