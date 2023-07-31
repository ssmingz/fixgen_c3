class PlaceHold {
  @Test
  public void testResourceCollections() {
    buildRule.executeTarget("testResourceCollection");
    assertFileExists(
        "junit.jar has been extracted",
        buildRule.getProject().getProperty("output")
            + "/unziptestout/junit/framework/Assert.class");
  }
}
