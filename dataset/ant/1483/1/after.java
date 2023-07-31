class PlaceHold {
  @Test
  public void test5() {
    buildRule.executeTarget("test5");
    String checkprop = buildRule.getProject().getProperty("file.wo.suf");
    assertEquals("foo", checkprop);
  }
}
