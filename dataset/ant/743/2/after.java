class PlaceHold {
  @Test
  public void testEmptyFile() throws Exception {
    buildRule.executeTarget("testEmptyFile");
    assertIndexCreated();
    assertContains("Required text not found in log", WARNING_EMPTY_FILE, buildRule.getLog());
  }
}
