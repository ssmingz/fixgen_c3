class PlaceHold {
  public void testNoCVSRoot() {
    try {
      executeTarget("test1");
      fail("BuildException not thrown");
    } catch (BuildException e) {
      assertEquals("cvsroot is required", e.getMessage());
    }
  }
}
