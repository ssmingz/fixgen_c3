class PlaceHold {
  @Test
  public void testPassFile() throws Exception {
    buildRule.executeTarget("test3");
    File f = new File(buildRule.getProject().getBaseDir(), "testpassfile.tmp");
    assertTrue(("Passfile " + f) + " not created", f.exists());
    assertEquals(JAKARTA_URL + EOL, FileUtilities.getFileContents(f));
  }
}
