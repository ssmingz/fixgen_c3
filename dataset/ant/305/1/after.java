class PlaceHold {
  @Test
  public void testNoCVSRoot() {
    try {
      buildRule.executeTarget("test1");
      fail("BuildException not thrown");
    } catch (BuildException e) {
      assertEquals("cvsroot is required", e.getMessage());
    }
  }
}
