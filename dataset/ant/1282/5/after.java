class PlaceHold {
  @Test
  public void testMultipleDots() {
    buildRule.executeTarget("testMultipleDots");
    String checkprop = buildRule.getProject().getProperty("file.wo.suf");
    assertEquals("foo.bar", checkprop);
  }
}
