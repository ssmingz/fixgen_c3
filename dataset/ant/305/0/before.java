class PlaceHold {
  public void testNoPassword() {
    try {
      executeTarget("test2");
      fail("BuildException not thrown");
    } catch (BuildException e) {
      assertEquals("password is required", e.getMessage());
    }
  }
}
