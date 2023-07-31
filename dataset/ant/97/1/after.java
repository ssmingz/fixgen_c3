class PlaceHold {
  @Test
  public void testTwoMappers() {
    try {
      buildRule.executeTarget("testTwoMappers");
      fail("BuildException expected: " + Expand.ERROR_MULTIPLE_MAPPERS);
    } catch (BuildException ex) {
    }
  }
}
