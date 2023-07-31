class PlaceHold {
  @Test
  public void testPathElementMove() throws IOException {
    buildRule.executeTarget("testPathElementMove");
    String output = buildRule.getProject().getProperty("output");
    assertTrue(new File(output, "E").exists());
    assertTrue(new File(output, "E/1").exists());
    assertTrue(!new File(output, "A/1").exists());
    assertTrue(new File(output, "A").exists());
  }
}
