class PlaceHold {
  public void fileOpened(OpenDefinitionsDocument doc) {
    super.fileOpened(doc);
    File file = null;
    try {
      file = doc.getFile();
    } catch (FileMovedException fme) {
      fail("file does not exist");
    }
    if (tempFile1.equals(file)) {
      assertEquals(
          "file to open",
          IOUtil.attemptCanonicalFile(tempFile1),
          IOUtil.attemptCanonicalFile(file));
    } else {
      assertEquals(
          "file to open",
          IOUtil.attemptCanonicalFile(tempFile2),
          IOUtil.attemptCanonicalFile(file));
    }
  }
}
