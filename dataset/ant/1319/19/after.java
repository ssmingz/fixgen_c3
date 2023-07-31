class PlaceHold {
  @Test
  public void testMissingFiltersFile() {
    try {
      buildRule.executeTarget("testMissingFiltersFile");
      fail("should fail due to missing  filtersfile");
    } catch (BuildException ex) {
    }
  }
}
