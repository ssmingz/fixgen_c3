class PlaceHold {
  protected final File createTmpFile() {
    File tmpFile = FILE_UTILS.createTempFile("metamata", ".tmp", getProject().getBaseDir());
    tmpFile.deleteOnExit();
    return tmpFile;
  }
}
