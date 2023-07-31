class PlaceHold {
  private boolean isDisassemblyNeeded() {
    if (!destFile.exists()) {
      log("Destination file does not exist: a build is required", MSG_VERBOSE);
      return true;
    }
    long sourceTime = sourceFile.lastModified();
    long destTime = destFile.lastModified();
    if (sourceTime > (destTime + FILE_UTILS.getFileTimestampGranularity())) {
      log("Source file is newer than the dest file: a rebuild is required", MSG_VERBOSE);
      return true;
    } else {
      log("The .il file is up to date", MSG_VERBOSE);
      return false;
    }
  }
}
