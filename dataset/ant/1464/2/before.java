class PlaceHold {
  public void testNotSelector() {
    expectBuildExceptionContaining(
        "not.selector",
        "checking for use as a selector (not allowed)",
        "fileset doesn\'t support the nested \"isfile");
  }
}
