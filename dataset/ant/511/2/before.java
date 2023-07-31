class PlaceHold {
  public void testEncoding() throws IOException {
    if (project.getJavaVersion() == Project.JAVA_1_1) {
      return;
    }
    executeTarget("testEncoding");
    assertEqualContent(
        new File("src/etc/testcases/taskdefs/fixcrlf/expected/input.lf.utf16"),
        new File("src/etc/testcases/taskdefs/fixcrlf/result/input.crlf.utf16"));
  }
}
