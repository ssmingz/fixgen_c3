class PlaceHold {
  @Test
  public void testNestedAB() {
    try {
      buildRule.executeTarget("nested.ab");
      fail("Build exception expected: Should have got ambiguous");
    } catch (BuildException ex) {
      AntAssert.assertContains("ambiguous", ex.getMessage());
    }
  }
}
