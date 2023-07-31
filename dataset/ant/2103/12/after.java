class PlaceHold {
  @Test
  public void testFilesmatchDifferentSizes() {
    buildRule.executeTarget("filesmatch-different-sizes");
    assertNull(buildRule.getProject().getProperty("filesmatch-different-sizes"));
  }
}
