class PlaceHold {
  @Test
  public void testIncompleteFile() throws Exception {
    buildRule.executeTarget("testIncompleteFile");
    assertIndexCreated();
    assertContains(
        "Required text not found in log", WARNING_IS_POSSIBLY_CORRUPTED, buildRule.getLog());
  }
}
