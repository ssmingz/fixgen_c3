class PlaceHold {
  protected void delFile(FTPClient ftp, String filename) throws IOException, TaskException {
    if (verbose) {
      getLogger().info("deleting " + filename);
    }
    if (!ftp.deleteFile(resolveFile(filename))) {
      String s = "could not delete file: " + ftp.getReplyString();
      if (skipFailedTransfers == true) {
        log(s, MSG_WARN);
        skipped++;
      } else {
        throw new TaskException(s);
      }
    } else {
      log((("File " + filename) + " deleted from ") + server, MSG_VERBOSE);
      transferred++;
    }
  }
}
