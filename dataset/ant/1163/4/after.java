class PlaceHold {
  protected void delFile(FTPClient ftp, String filename) throws IOException, TaskException {
    if (verbose) {
      getLogger().info("deleting " + filename);
    }
    if (!ftp.deleteFile(resolveFile(filename))) {
      String s = "could not delete file: " + ftp.getReplyString();
      if (skipFailedTransfers == true) {
        getLogger().warn(s);
        skipped++;
      } else {
        throw new TaskException(s);
      }
    } else {
      getLogger().debug((("File " + filename) + " deleted from ") + server);
      transferred++;
    }
  }
}
