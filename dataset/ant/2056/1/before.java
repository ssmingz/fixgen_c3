class PlaceHold {
  public void test3() {
    expectBuildExceptionContaining(
        "test3", "Manifest is invalid - no colon on header line", "Invalid Manifest");
  }
}
