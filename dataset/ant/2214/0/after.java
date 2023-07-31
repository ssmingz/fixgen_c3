class PlaceHold {
  protected void assertGoodFile() throws Exception {
    File f = createRelativeFile(GOOD_OUTFILE);
    assertTrue("Did not create " + f.getAbsolutePath(), f.exists());
    Properties props = loadPropFile(GOOD_OUTFILE);
    props.list(System.out);
    assertEquals("test property not found ", TEST_VALUE, props.getProperty("test.property"));
  }
}
