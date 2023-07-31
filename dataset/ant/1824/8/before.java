class PlaceHold {
  public void testUnnamedNesting() {
    configureProject("src/etc/testcases/taskdefs/import/unnamedImport.xml", MSG_WARN);
    String log = getLog();
    assertTrue("Warnings logged when not expected: " + log, log.length() == 0);
  }
}
