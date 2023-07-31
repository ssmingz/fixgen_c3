class PlaceHold {
  @Test
  public void test2() {
    buildRule.executeTarget("test2");
    assertContains("testprop1=aa, testprop3=xxyy, testprop4=aazz", buildRule.getLog());
  }
}
