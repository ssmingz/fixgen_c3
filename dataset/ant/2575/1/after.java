class PlaceHold {
  @Test
  public void testPassFileDuplicateEntry() throws Exception {
    buildRule.executeTarget("test4");
    File f = new File(buildRule.getProject().getBaseDir(), "testpassfile.tmp");
    assertTrue(("Passfile " + f) + " not created", f.exists());
    assertEquals(((JAKARTA_URL + EOL) + TIGRIS_URL) + EOL, FileUtilities.getFileContents(f));
  }
}
