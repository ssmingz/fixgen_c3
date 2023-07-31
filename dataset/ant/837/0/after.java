class PlaceHold {
  @Test
  public void testPlainTask() {
    buildRule.executeTarget("testPlainTask");
    Echo e = ((Echo) (buildRule.getProject().getReference("echo")));
    assertFalse(e.getLocation() == Location.UNKNOWN_LOCATION);
    assertFalse(e.getLocation().getLineNumber() == 0);
  }
}
