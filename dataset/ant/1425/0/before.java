class PlaceHold {
  public void testNoResourceOnErrorFail() {
    expectLogContaining("noresourcefail", "Could not load definitions from resource ");
  }
}
