class PlaceHold {
  @Test
  public void testDoubleAttribute() {
    try {
      buildRule.executeTarget("doubleAttributeDef");
      fail("Should have detected duplicate attirbute definition");
    } catch (BuildException ex) {
      AntAssert.assertContains("attr1 attribute more than once", ex.getMessage());
    }
  }
}
