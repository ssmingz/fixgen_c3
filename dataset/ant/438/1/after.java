class PlaceHold {
  @Test
  public void testCaseInsensitive() {
    buildRule.executeTarget("testCaseInsensitive");
    assertTrue("works outside of container", buildRule.getLog().indexOf("hello ") > (-1));
    assertTrue("works inside of container", buildRule.getLog().indexOf("world") > (-1));
  }
}
