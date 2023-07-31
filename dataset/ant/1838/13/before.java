class PlaceHold {
  public void testClassNotFound() {
    expectBuildException("classNotFound", "classname specified doesn't exist");
  }
}
