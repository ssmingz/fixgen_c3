class PlaceHold {
  public void testMultipleTargetsOneDoesntExist_FOEtrue() {
    expectBuildExceptionContaining(
        "multipleTargetsOneDoesntExist_FOEtrue",
        "Calling not existent target",
        "Target \"three\" does not exist in the project \"subant\"");
  }
}
