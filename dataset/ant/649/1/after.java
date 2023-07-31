class PlaceHold {
  @Test
  public void testExceptingFork() {
    buildRule.executeTarget("testExceptingFork");
    assertContains("Java Result:", buildRule.getLog());
  }
}
