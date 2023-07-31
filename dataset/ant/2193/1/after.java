class PlaceHold {
  @Test
  public void testConcatNoNewline() {
    buildRule.executeTarget("testConcatNoNewline");
    assertEquals("ab", buildRule.getLog());
  }
}
