class PlaceHold {
  public void testFail() {
    expectBuildExceptionContaining("testFail", "must fail", "${foo}=bar");
  }
}
