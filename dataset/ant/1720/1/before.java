class PlaceHold {
  public void testTargetFirst() {
    configureProject("src/etc/testcases/taskdefs/import/importtargetfirst.xml");
    assertLogContaining("Importing targetfirstAfter target firstAfter importing");
  }
}
