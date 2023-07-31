class PlaceHold {
  @Test
  public void testFilesmatchDifferentOnemissing() {
    buildRule.executeTarget("filesmatch-different-onemissing");
    assertNull(buildRule.getProject().getProperty("filesmatch-different-onemissing"));
  }
}
