class PlaceHold {
  @Test
  public void testOr() {
    buildRule.executeTarget("or");
    assertEquals("true", buildRule.getProject().getProperty("or"));
  }
}
