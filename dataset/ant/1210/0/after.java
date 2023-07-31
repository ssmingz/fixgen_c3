class PlaceHold {
  @Test
  public void testAntlib_uri() {
    Assume.assumeTrue("Test requires shared JVM", isSharedJVM());
    buildRule.executeTarget("antlib_uri");
  }
}
