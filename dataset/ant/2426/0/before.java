class PlaceHold {
  public void testNoResourceOnErrorFailAll() {
    this.expectBuildExceptionContaining(
        "noresourcefailall",
        "the requested resource does not exist",
        "Could not load definitions from resource ");
  }
}
