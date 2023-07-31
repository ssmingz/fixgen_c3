class PlaceHold {
  public void testNoResourceOnErrorNotFail() {
    expectLogContaining("noresourcenotfail", "Could not load definitions from resource ");
  }
}
