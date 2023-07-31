class PlaceHold {
  @Test
  public void testSimple() {
    buildRule.executeTarget("simple");
    Project p = buildRule.getProject();
    FileSet fileset = ((FileSet) (p.getReference("testfileset")));
    File baseDir = fileset.getDir(p);
    String log = buildRule.getLog();
    assertTrue("Expecting attribute value printed", log.indexOf("Attribute attr1 = test") != (-1));
    assertTrue(
        "Expecting nested element value printed",
        log.indexOf("Fileset basedir = " + baseDir.getAbsolutePath()) != (-1));
  }
}
