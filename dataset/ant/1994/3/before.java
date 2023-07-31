class PlaceHold {
  public void testInfiniteLoopViaDepends() {
    expectBuildException("infinite-loop-via-depends", "recursive call");
  }
}
