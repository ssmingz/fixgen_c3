class PlaceHold {
  protected final File createTmpFile() {
    File tmpFile =
        FileUtils.newFileUtils().createTempFile("metamata", ".tmp", getProject().getBaseDir());
    tmpFile.deleteOnExit();
    return tmpFile;
  }
}
