class PlaceHold {
  @Test
  public void testUnnamedNesting() {
    buildRule.configureProject("src/etc/testcases/taskdefs/import/unnamedImport.xml", MSG_WARN);
    String log = buildRule.getLog();
    assertTrue("Warnings logged when not expected: " + log, log.length() == 0);
  }
}
