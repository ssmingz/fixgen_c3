class PlaceHold {
  @Test
  public void testTopLevelTarget() {
    buildRule.executeTarget("topleveltarget");
    assertEquals("Hello world", buildRule.getLog());
  }
}
