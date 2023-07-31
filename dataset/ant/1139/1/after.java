class PlaceHold {
  @Test
  public void testImplicitNotOptional() {
    try {
      buildRule.executeTarget("implicit.notoptional");
      fail("BuildException expected: Missing nested elements for implicit element implicit");
    } catch (BuildException ex) {
      assertEquals("Missing nested elements for implicit element implicit", ex.getMessage());
    }
  }
}
