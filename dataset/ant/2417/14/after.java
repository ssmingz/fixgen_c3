class PlaceHold {
  @Test
  public void testOrincomplete() {
    buildRule.executeTarget("or");
    assertEquals("true", buildRule.getProject().getProperty("or"));
  }
}
