class PlaceHold {
  @Test
  public void testPassFileMultipleEntry() throws Exception {
    buildRule.executeTarget("test5");
    File f = new File(buildRule.getProject().getBaseDir(), "testpassfile.tmp");
    assertTrue(("Passfile " + f) + " not created", f.exists());
    assertEquals(
        ((((JAKARTA_URL + EOL) + XML_URL) + EOL) + TIGRIS_URL) + EOL,
        FileUtilities.getFileContents(f));
  }
}
