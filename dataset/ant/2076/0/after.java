class PlaceHold {
  @Test
  public void testTaskAdapter() {
    buildRule.executeTarget("taskadapter");
    assertContains("MyExec called", buildRule.getLog());
  }
}
