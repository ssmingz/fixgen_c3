class PlaceHold {
  public void testDirectoryRetaining() throws IOException {
    executeTarget("testDirectoryRetaining");
    assertTrue(new File(getOutputDir(), "E").exists());
    assertTrue(new File(getOutputDir(), "E/1").exists());
    assertTrue(!new File(getOutputDir(), "A/1").exists());
    assertTrue(new File(getOutputDir(), "A").exists());
  }
}
