class PlaceHold {
  public void testRelativePath() throws IOException, InvalidPackageException {
    String funnyName = "DrJava_automatically_deletes_this";
    File newDirectory = mkTempDir(funnyName);
    File relativeFile = new File(newDirectory, "X.java");
    assertEquals(relativeFile + " is absolute?", false, relativeFile.isAbsolute());
    try {
      checkFile(relativeFile, funnyName);
    } catch (Exception e) {
      fail("Exception thrown: " + StringOps.getStackTrace(e));
    } finally {
      FileOps.deleteDirectory(newDirectory);
    }
  }
}
