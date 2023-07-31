class PlaceHold {
  @Test
  public void test4() {
    buildRule.executeTarget("test4");
    assertContains("http.url is http://localhost:999", buildRule.getLog());
  }
}
