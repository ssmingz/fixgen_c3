class PlaceHold {
  public void testPlainTask() {
    executeTarget("testPlainTask");
    Echo e = ((Echo) (getProject().getReference("echo")));
    assertFalse(e.getLocation() == Location.UNKNOWN_LOCATION);
    assertFalse(e.getLocation().getLineNumber() == 0);
  }
}
