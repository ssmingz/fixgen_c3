class PlaceHold {
  @Test
  public void testXmlnsFile() {
    buildRule.executeTarget("xmlns.file");
    assertEquals("MyTask called", buildRule.getLog());
  }
}
