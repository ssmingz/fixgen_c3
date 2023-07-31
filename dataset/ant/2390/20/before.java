class PlaceHold {
  private boolean isExecuteNeeded() {
    if (!destFile.exists()) {
      log("Destination file does not exist: a build is required", MSG_VERBOSE);
      return true;
    }
    long sourceTime = srcFile.lastModified();
    long destTime = destFile.lastModified();
    if (sourceTime > (destTime + FileUtils.newFileUtils().getFileTimestampGranularity())) {
      log("Source file is newer than the dest file: a rebuild is required", MSG_VERBOSE);
      return true;
    } else {
      log("The output file is up to date", MSG_VERBOSE);
      return false;
    }
  }
}
