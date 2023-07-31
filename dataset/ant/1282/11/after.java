class PlaceHold {
  @Test
  public void testValueEqualsSuffixWithoutDot() {
    buildRule.executeTarget("testValueEqualsSuffixWithoutDot");
    String checkprop = buildRule.getProject().getProperty("file.wo.suf");
    assertEquals("", checkprop);
  }
}
