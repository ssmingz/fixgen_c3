class PlaceHold {
  public void testSimpleImport() {
    configureProject("src/etc/testcases/taskdefs/import/import.xml");
    assertLogContaining("Before importIn imported topAfter import");
  }
}
