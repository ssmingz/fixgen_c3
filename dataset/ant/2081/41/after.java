class PlaceHold {
  @Test
  public void testNoDots() {
    buildRule.executeTarget("testNoDots");
    String checkprop = buildRule.getProject().getProperty("file.wo.suf");
    assertEquals("foo.bar", checkprop);
  }
}
