class PlaceHold {
  @Test
  public void testMacrodefWrappedTask() {
    buildRule.executeTarget("testMacrodefWrappedTask");
    Echo e = ((Echo) (buildRule.getProject().getReference("echo3")));
    assertTrue(buildRule.getLog().indexOf("Line: " + (e.getLocation().getLineNumber() + 1)) > (-1));
  }
}
