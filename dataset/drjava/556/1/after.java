class PlaceHold {
  public void testDotPaths() {
    String funnyName = "DrJava_automatically_deletes_this_2";
    File newDirectory = mkTempDir(funnyName);
    assertTrue("child directory created OK", new File(newDirectory, "childDir").mkdir());
    File relativeFile = new File(newDirectory, "./X.java");
    File relativeFile2 = new File(newDirectory, ".\\Y.java");
    File relativeFile3 = new File(newDirectory, "childDir/../Z.java");
    try {
      checkFile(relativeFile, funnyName);
      checkFile(relativeFile2, funnyName);
      checkFile(relativeFile3, funnyName);
    } catch (Exception e) {
      fail("Exception thrown: " + StringOps.getStackTrace(e));
    } finally {
      FileOps.deleteDirectoryOnExit(newDirectory);
    }
  }
}
