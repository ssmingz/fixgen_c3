class PlaceHold {
  @Test
  public void testNoPassword() {
    try {
      buildRule.executeTarget("test2");
      fail("BuildException not thrown");
    } catch (BuildException e) {
      assertEquals("password is required", e.getMessage());
    }
  }
}
