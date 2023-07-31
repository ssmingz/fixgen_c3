class PlaceHold {
  @Test
  public void testWorks() {
    buildRule.executeTarget("testWorks");
    assertPropertyContains("testWorks", "file:");
    assertPropertyContains("testWorks", "/foo");
  }
}
