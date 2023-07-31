class PlaceHold {
  @Test
  public void testAntlib_uri_auto2() {
    Assume.assumeTrue("Test requires shared JVM", isSharedJVM());
    buildRule.executeTarget("antlib_uri_auto2");
  }
}
