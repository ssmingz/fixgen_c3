class PlaceHold {
  @Test
  public void testWrongClass() throws Exception {
    try {
      buildRule.executeTarget("testWrongClass");
      fail("Class not an RMIC adapter");
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_NOT_RMIC_ADAPTER, ex.getMessage());
    }
  }
}
