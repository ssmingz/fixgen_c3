class PlaceHold {
  @Test
  public void testUtf8() {
    try {
      buildRule.executeTarget("testUtf8");
      fail("Invalid characters in file");
    } catch (BuildException ex) {
    }
  }
}
