class PlaceHold {
  public void testPresetdefWrappedTask() {
    executeTarget("testPresetdefWrappedTask");
    Echo e = ((Echo) (getProject().getReference("echo4")));
    assertTrue(getLog().indexOf("Line: " + (e.getLocation().getLineNumber() + 1)) > (-1));
  }
}
