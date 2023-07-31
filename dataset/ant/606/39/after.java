class PlaceHold {
  @Test
  public void testCore() {
    buildRule.executeTarget("core");
    assertEquals("MyTask called", buildRule.getLog());
  }
}
