class PlaceHold {
  @Test
  public void testTrimignore() throws IOException {
    buildRule.executeTarget("trimignore");
    assertContains("Hello-World", buildRule.getLog());
  }
}
