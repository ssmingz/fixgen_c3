class PlaceHold {
  @Test
  public void test1() {
    buildRule.executeTarget("test1");
    assertTrue(buildRule.getProject().<Object>getReference("test1") instanceof RedirectorElement);
  }
}
