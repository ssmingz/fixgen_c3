class PlaceHold {
  public void testPassFileDuplicateEntry() throws Exception {
    executeTarget("test4");
    File f = new File(getProjectDir(), "testpassfile.tmp");
    assertTrue(("Passfile " + f) + " not created", f.exists());
    assertEquals(((JAKARTA_URL + EOL) + TIGRIS_URL) + EOL, readFile(f));
  }
}
