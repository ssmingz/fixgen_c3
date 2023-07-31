class PlaceHold {
  public void execute() throws TaskException {
    validate();
    final FileSet fileSet = getFileSet();
    fileSet.setDir(m_basedir);
    final DirectoryScanner scanner = ScannerUtil.getDirectoryScanner(fileSet);
    prepareProcessor();
    if ((m_in != null) && (m_out != null)) {
      processSingleFile(m_in, m_out);
      return;
    }
    final String message = "Transforming into " + m_destdir;
    getContext().info(message);
    processFiles(scanner);
    processDirs(scanner);
  }
}
