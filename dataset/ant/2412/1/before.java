class PlaceHold {
  public void test1() {
    expectBuildExceptionContaining("test1", "it is required to fail :-)", "No message");
  }
}
