class PlaceHold {
  protected DirectoryScanner getDirectoryScanner(final File baseDir) throws TaskException {
    m_fileset.setDir(baseDir);
    m_fileset.setDefaultexcludes(m_useDefaultExcludes);
    return ScannerUtil.getDirectoryScanner(m_fileset);
  }
}
