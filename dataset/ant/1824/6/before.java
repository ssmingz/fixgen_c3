class PlaceHold {
  public void testPrefixAddsDir() throws IOException {
    executeTarget("testPrefixAddsDir");
    File archive = new File(getProject().getProperty("output"), "test3.zip");
    zfPrefixAddsDir = new ZipFile(archive);
    ZipEntry ze = zfPrefixAddsDir.getEntry("test/");
    assertNotNull("test/ has been added", ze);
  }
}
