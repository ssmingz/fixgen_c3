class PlaceHold {
  public void testSimple() {
    executeTarget("simple");
    Project p = getProject();
    FileSet fileset = ((FileSet) (p.getReference("testfileset")));
    File baseDir = fileset.getDir(p);
    String log = getLog();
    assertTrue("Expecting attribute value printed", log.indexOf("Attribute attr1 = test") != (-1));
    assertTrue(
        "Expecting nested element value printed",
        log.indexOf("Fileset basedir = " + baseDir.getAbsolutePath()) != (-1));
  }
}
