class PlaceHold {
  public void testReferenceAbuse() {
    expectBuildExceptionContaining(
        "test-reference-abuse", "reference abuse rejected", "You must not specify");
  }
}
