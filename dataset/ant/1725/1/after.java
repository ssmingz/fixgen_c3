class PlaceHold {
  @Test
  public void testNestedByClassName() {
    buildRule.executeTarget("nestedbyclassname");
    Project p = buildRule.getProject();
    FileSet fileset = ((FileSet) (p.getReference("testfileset")));
    File baseDir = fileset.getDir(p);
    String log = buildRule.getLog();
    assertTrue(
        "Expecting attribute value to be printed", log.indexOf("Attribute attr1 = test") != (-1));
    assertTrue(
        "Expecting nested element value to be printed",
        log.indexOf("Fileset basedir = " + baseDir.getAbsolutePath()) != (-1));
  }
}
