class PlaceHold {
  public void testMultipleTargetsOneDoesntExist_FOEfalse() {
    executeTarget("multipleTargetsOneDoesntExist_FOEfalse");
    assertLogContaining("Target \"three\" does not exist in the project \"subant\"");
  }
}
