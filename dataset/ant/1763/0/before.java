class PlaceHold {
  public void testPassFile() throws Exception {
    executeTarget("test3");
    File f = new File(getProjectDir(), "testpassfile.tmp");
    assertTrue(("Passfile " + f) + " not created", f.exists());
    assertEquals(JAKARTA_URL + EOL, readFile(f));
  }
}
