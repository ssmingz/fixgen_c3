class PlaceHold {
  @Test
  public void testEncoding() {
    try {
      buildRule.executeTarget("encoding");
      fail("<untar> overrides setEncoding.");
    } catch (BuildException ex) {
      assertEquals("The untar task doesn't support the encoding attribute", ex.getMessage());
    }
  }
}
