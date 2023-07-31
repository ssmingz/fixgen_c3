class PlaceHold {
  @Test
  public void testPath() {
    buildRule.executeTarget("path");
    AntAssert.assertContains("types.Path", buildRule.getLog());
  }
}
