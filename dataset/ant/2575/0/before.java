class PlaceHold {
  public void testPassFileMultipleEntry() throws Exception {
    executeTarget("test5");
    File f = new File(getProjectDir(), "testpassfile.tmp");
    assertTrue(("Passfile " + f) + " not created", f.exists());
    assertEquals(((((JAKARTA_URL + EOL) + XML_URL) + EOL) + TIGRIS_URL) + EOL, readFile(f));
  }
}
