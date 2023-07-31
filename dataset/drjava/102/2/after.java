class PlaceHold {
  public void testDeleteDirectoryOnExit() throws IOException, InterruptedException {
    File dir1 = FileOps.createTempDirectory("DrJavaTestTempDir");
    assertTrue("dir1 exists", dir1.exists());
    File file1 = File.createTempFile("DrJavaTest-", ".temp", dir1);
    assertTrue("file1 exists", file1.exists());
    File dir2 = FileOps.createTempDirectory("TempDir", dir1);
    assertTrue("dir2 exists", dir2.exists());
    File file2 = File.createTempFile("DrJavaTest-", ".temp", dir2);
    assertTrue("file2 exists", file2.exists());
    String className = "edu.rice.cs.util.FileOpsTest";
    String[] args = new String[] {dir1.getAbsolutePath()};
    Process process = ExecJVM.runJVMPropagateClassPath(className, args, NULL_FILE);
    int status = process.waitFor();
    assertEquals("Delete on exit test exited with an error!", 0, status);
    assertTrue("dir1 should be deleted", !dir1.exists());
    assertTrue("file1 should be deleted", !file1.exists());
    assertTrue("dir2 should be deleted", !dir2.exists());
    assertTrue("file2 should be deleted", !file2.exists());
  }
}
