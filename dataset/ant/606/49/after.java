class PlaceHold {
  @Test
  public void test4() {
    buildRule.executeTarget("test4");
    assertEquals(
        "DEPRECATED - The copydir task is deprecated.  Use copy instead.Warning: src == dest",
        buildRule.getLog());
  }
}
