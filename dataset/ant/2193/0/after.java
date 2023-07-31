class PlaceHold {
  @Test
  public void testConcatNoNewlineEncoding() {
    buildRule.executeTarget("testConcatNoNewlineEncoding");
    assertEquals("ab", buildRule.getLog());
  }
}
