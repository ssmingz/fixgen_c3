class PlaceHold {
  @Test
  public void testMultipleTargetsOneDoesntExist_FOEfalse() {
    buildRule.executeTarget("multipleTargetsOneDoesntExist_FOEfalse");
    assertContains("Target \"three\" does not exist in the project \"subant\"", buildRule.getLog());
  }
}
