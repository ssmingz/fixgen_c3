class PlaceHold {
  @Test
  public void testfileheader() {
    test3();
    buildRule.executeTarget("testfileheader");
    assertEquals("Hello, World!Hello, World!", buildRule.getLog());
  }
}
