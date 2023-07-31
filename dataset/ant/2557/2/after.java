class PlaceHold {
  @Test
  public void test4() {
    buildRule.executeTarget("test4");
    String checkprop = buildRule.getProject().getProperty("file.w.suf");
    assertEquals("foo.txt", checkprop);
  }
}
