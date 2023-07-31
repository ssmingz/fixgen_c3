class PlaceHold {
  @Test
  public void testDTD() {
    buildRule.executeTarget("testdtd");
    assertEquals("Text", buildRule.getProject().getProperty("root-tag.inner-tag"));
  }
}
