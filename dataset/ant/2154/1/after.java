class PlaceHold {
  @Test
  public void testEncoding() {
    buildRule.executeTarget("encodingTest");
    assertFileExists(
        "foo has been properly named",
        buildRule.getProject().getProperty("output") + "/unziptestout/foo");
  }
}
