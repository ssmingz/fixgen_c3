class PlaceHold {
  @Test
  public void testDirectoryRetaining() throws IOException {
    buildRule.executeTarget("testDirectoryRetaining");
    String output = buildRule.getProject().getProperty("output");
    assertTrue(new File(output, "E").exists());
    assertTrue(new File(output, "E/1").exists());
    assertTrue(!new File(output, "A/1").exists());
    assertTrue(new File(output, "A").exists());
  }
}
