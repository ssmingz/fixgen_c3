class PlaceHold {
  @Test
  public void testFilesmatchMatch() {
    buildRule.executeTarget("filesmatch-match");
    assertEquals("true", buildRule.getProject().getProperty("filesmatch-match"));
  }
}
