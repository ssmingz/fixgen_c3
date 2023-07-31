class PlaceHold {
  @Test
  public void test5() {
    buildRule.executeTarget("test5");
    String output = buildRule.getLog();
    boolean hasWarning =
        output.indexOf("Manifest warning: \"Name\" attributes should not occur in the main section")
            != (-1);
    assertTrue("Expected warning about Name in main section", hasWarning);
  }
}
