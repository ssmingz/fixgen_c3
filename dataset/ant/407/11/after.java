class PlaceHold {
  @Test
  public void testAddConfiguredValue() {
    buildRule.executeTarget("myaddconfiguredvalue");
    AntAssert.assertContains("value is Value Setexecute: value is Value Set", buildRule.getLog());
  }
}
