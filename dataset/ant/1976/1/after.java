class PlaceHold {
  @Test
  public void testFilesmatchExistence() {
    buildRule.executeTarget("filesmatch-existence");
    assertNull(buildRule.getProject().getProperty("filesmatch-existence"));
  }
}
