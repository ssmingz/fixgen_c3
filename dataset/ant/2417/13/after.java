class PlaceHold {
  @Test
  public void testBasic() {
    buildRule.executeTarget("basic");
    assertEquals("true", buildRule.getProject().getProperty("basic"));
  }
}
