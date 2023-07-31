class PlaceHold {
  @Test
  public void testExcluded() {
    try {
      buildRule.executeTarget("excluded");
      fail("BuildException expected: excluded uri");
    } catch (BuildException ex) {
      assertEquals("Attempt to use a reserved URI ant:notallowed", ex.getMessage());
    }
  }
}
