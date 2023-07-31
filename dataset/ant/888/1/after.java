class PlaceHold {
  @Test
  public void testPresetdefWrappedTask() {
    buildRule.executeTarget("testPresetdefWrappedTask");
    Echo e = ((Echo) (buildRule.getProject().getReference("echo4")));
    assertTrue(buildRule.getLog().indexOf("Line: " + (e.getLocation().getLineNumber() + 1)) > (-1));
  }
}
