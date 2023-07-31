class PlaceHold {
  public void testPathElementMove() throws IOException {
    executeTarget("testPathElementMove");
    assertTrue(new File(getOutputDir(), "E").exists());
    assertTrue(new File(getOutputDir(), "E/1").exists());
    assertTrue(!new File(getOutputDir(), "A/1").exists());
    assertTrue(new File(getOutputDir(), "A").exists());
  }
}
