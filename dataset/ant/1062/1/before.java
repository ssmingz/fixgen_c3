class PlaceHold {
  public void testClassnameAndJar() {
    expectBuildException("testClassnameAndJar", "Should not be able to set both classname AND jar");
  }
}
