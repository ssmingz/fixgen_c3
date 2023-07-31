class PlaceHold {
  @Test
  public void testMagicPropertyOverridesEmptyString() throws Exception {
    try {
      buildRule.executeTarget("testMagicPropertyOverridesEmptyString");
      fail("Magic property not working");
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_UNKNOWN_COMPILER, ex.getMessage());
    }
  }
}
