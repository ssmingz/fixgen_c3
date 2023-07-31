class PlaceHold {
  @Test
  public void testImplicitExplicit() {
    try {
      buildRule.executeTarget("implicit.explicit");
      fail("BuildException expected: Only one element allowed when using implicit elements");
    } catch (BuildException ex) {
      assertEquals("Only one element allowed when using implicit elements", ex.getMessage());
    }
  }
}
