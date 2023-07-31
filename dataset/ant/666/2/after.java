class PlaceHold {
  @Test
  public void testAntlibResource() {
    buildRule.executeTarget("antlib.resource");
    assertEquals("MyTask called-and-then-MyTask2 called", buildRule.getLog());
  }
}
