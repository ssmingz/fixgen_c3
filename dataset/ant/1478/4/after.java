class PlaceHold {
  @Test
  public void testAntlibFile() {
    buildRule.executeTarget("antlib.file");
    assertEquals("MyTask called", buildRule.getLog());
  }
}
