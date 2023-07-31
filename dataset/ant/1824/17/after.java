class PlaceHold {
  @Test
  public void test7() {
    buildRule.executeTarget("test7");
    boolean hasWarning = buildRule.getLog().indexOf(ERROR_FROM_FORBIDDEN) != (-1);
    assertTrue("Expected warning about From: attribute", hasWarning);
  }
}
