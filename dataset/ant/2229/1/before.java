class PlaceHold {
  public void test7() {
    executeTarget("test7");
    boolean hasWarning = getLog().indexOf(ERROR_FROM_FORBIDDEN) != (-1);
    assertEquals("Expected warning about From: attribute", true, hasWarning);
  }
}
