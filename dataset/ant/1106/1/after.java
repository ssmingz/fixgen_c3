class PlaceHold {
  @Test
  public void testFilesmatchDifferent() {
    buildRule.executeTarget("filesmatch-different");
    assertNull(buildRule.getProject().getProperty("filesmatch-different"));
  }
}
