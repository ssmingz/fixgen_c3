class PlaceHold {
  @Test
  public void testOnErrorReport() {
    buildRule.executeTarget("onerror.report");
    assertContains("MyTaskNotPresent cannot be found", buildRule.getLog());
  }
}
