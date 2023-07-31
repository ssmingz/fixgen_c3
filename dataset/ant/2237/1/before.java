class PlaceHold {
  protected FileSystem createFileSystem(final ParsedUri uri) throws FileSystemException {
    final ParsedFtpUri ftpUri = ((ParsedFtpUri) (uri));
    final FileName rootName = new DefaultFileName(m_parser, ftpUri.getRootUri(), "/");
    String username = ftpUri.getUserName();
    if (username == null) {
      username = "anonymous";
    }
    String password = ftpUri.getPassword();
    if (password == null) {
      password = "anonymous";
    }
    return new FtpFileSystem(rootName, ftpUri.getHostName(), username, password);
  }
}
