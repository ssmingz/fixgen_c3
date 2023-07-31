class PlaceHold {
  @Test
  public void testBadName() throws Exception {
    try {
      buildRule.executeTarget("testBadName");
      fail("Compile not known");
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_UNKNOWN_COMPILER, ex.getMessage());
    }
  }
}
