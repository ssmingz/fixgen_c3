class PlaceHold {
  public void testTaskCase() {
    expectBuildExceptionContaining(
        "taskcase", "Task names are case sensitive", "Problem: failed to create task or type ecHO");
  }
}
