class PlaceHold {
  public void testResourceCollections() {
    executeTarget("testResourceCollection");
    assertFileExists("junit.jar has been extracted", "unziptestout/junit/framework/Assert.class");
  }
}
