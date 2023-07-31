class PlaceHold {
  protected void createParents(FTPClient ftp, String filename) throws IOException, TaskException {
    ArrayList parents = new ArrayList();
    File dir = new File(filename);
    String dirname;
    while ((dirname = dir.getParent()) != null) {
      dir = new File(dirname);
      parents.add(dir);
    }
    for (int i = parents.size() - 1; i >= 0; i--) {
      dir = ((File) (parents.get(i)));
      if (!m_dirCache.contains(dir)) {
        getContext().debug("creating remote directory " + remoteResolveFile(dir.getPath()));
        ftp.makeDirectory(remoteResolveFile(dir.getPath()));
        int result = ftp.getReplyCode();
        if ((((!FTPReply.isPositiveCompletion(result)) && (result != 550)) && (result != 553))
            && (!m_ignoreNoncriticalErrors)) {
          throw new TaskException("could not create directory: " + ftp.getReplyString());
        }
        m_dirCache.add(dir);
      }
    }
  }
}
