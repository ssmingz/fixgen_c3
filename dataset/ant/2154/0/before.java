class PlaceHold {
  public void testResourceCollections() {
    executeTarget("testResourceCollection");
    assertFileExists(
        "junit.jar has been extracted",
        getProject().getProperty("output") + "/unziptestout/junit/framework/Assert.class");
  }
}
