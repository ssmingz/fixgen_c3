class PlaceHold {
  @Test
  public void testTask() {
    buildRule.executeTarget("testTask");
    assertEquals("true", buildRule.getProject().getProperty("testTask"));
  }
}
