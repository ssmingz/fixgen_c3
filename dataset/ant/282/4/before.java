class PlaceHold {
  public void testConditionConditionTask() {
    expectBuildExceptionContaining(
        "condition.condition.task", "task masking condition", "doesn't support the nested");
  }
}
