class PlaceHold {
  public void testRun2() throws Exception {
    expectBuildExceptionContaining(
        "testRun2", "exception thrown by the subclass", "executing the Foo task");
  }
}
