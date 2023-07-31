class PlaceHold {
  @Test
  public void test6() {
    buildRule.executeTarget("test6");
    assertEquals("scott", buildRule.getProject().getProperty("db.user"));
  }
}
