class PlaceHold {
  protected void delFile(FTPClient ftp, String filename) throws IOException, TaskException {
    if (m_verbose) {
      getContext().info("deleting " + filename);
    }
    if (!ftp.deleteFile(remoteResolveFile(filename))) {
      String s = "could not delete file: " + ftp.getReplyString();
      if (m_skipFailedTransfers == true) {
        getContext().warn(s);
        m_skipped++;
      } else {
        throw new TaskException(s);
      }
    } else {
      getContext().debug((("File " + filename) + " deleted from ") + m_server);
      m_transferred++;
    }
  }
}
