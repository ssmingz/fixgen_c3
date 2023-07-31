class PlaceHold {
  @Test
  public void testNested() {
    buildRule.executeTarget("nested");
    assertEquals("A nested element", buildRule.getLog());
  }
}
