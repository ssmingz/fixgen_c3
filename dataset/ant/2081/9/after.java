class PlaceHold {
  @Test
  public void testValueEqualsSuffixWithDot() {
    buildRule.executeTarget("testValueEqualsSuffixWithDot");
    String checkprop = buildRule.getProject().getProperty("file.wo.suf");
    assertEquals("", checkprop);
  }
}
