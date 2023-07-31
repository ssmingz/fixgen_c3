class PlaceHold {
  @Test
  public void testNotSelector() {
    try {
      buildRule.executeTarget("not.selector");
      fail("Exception should have been thrown: checking for use as a selector (not allowed)");
    } catch (BuildException ex) {
      AntAssert.assertContains("fileset doesn\'t support the nested \"isfile", ex.getMessage());
    }
  }
}
