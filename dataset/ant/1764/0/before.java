class PlaceHold {
  public void testMacrodefWrappedTask() {
    executeTarget("testMacrodefWrappedTask");
    Echo e = ((Echo) (getProject().getReference("echo3")));
    assertTrue(getLog().indexOf("Line: " + (e.getLocation().getLineNumber() + 1)) > (-1));
  }
}
