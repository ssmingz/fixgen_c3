class PlaceHold {
  public void testDTD() {
    executeTarget("testdtd");
    assertEquals("Text", getProject().getProperty("root-tag.inner-tag"));
  }
}
