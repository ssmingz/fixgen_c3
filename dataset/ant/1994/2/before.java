class PlaceHold {
  public void testBlankTarget() {
    expectBuildException("blank-target", "target name must not be empty");
  }
}
