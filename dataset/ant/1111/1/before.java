class PlaceHold {
  public void testRun() throws Exception {
    expectBuildExceptionContaining(
        "testRun", "exception thrown by the subclass", "executing the Foo task");
  }
}
