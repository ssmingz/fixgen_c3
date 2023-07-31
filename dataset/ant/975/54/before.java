class PlaceHold {
  protected void listFile(FTPClient ftp, BufferedWriter bw, String filename)
      throws IOException, TaskException {
    if (m_verbose) {
      getLogger().info("listing " + filename);
    }
    FTPFile ftpfile = ftp.listFiles(remoteResolveFile(filename))[0];
    bw.write(ftpfile.toString());
    bw.newLine();
    m_transferred++;
  }
}
