class PlaceHold {
  public void test4() {
    expectBuildExceptionContaining(
        "test4", "Manifest is invalid - section starts with continuation line", "Invalid Manifest");
  }
}
