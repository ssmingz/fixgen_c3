class PlaceHold {
  @Test
  public void testContainsAnycase() {
    buildRule.executeTarget("contains-anycase");
    assertEquals("true", buildRule.getProject().getProperty("contains-anycase"));
  }
}
