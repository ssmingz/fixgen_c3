class PlaceHold {
  public void testNoProperty() {
    expectBuildExceptionContaining("testNoProperty", "missing property", "property");
  }
}
