class PlaceHold {
  public void execute() throws TaskException {
    validate();
    if (m_file != null) {
      if (null == m_destFile) {
        m_destFile = new File(m_destDir, m_file.getName());
      }
      if (m_overwrite || (m_file.lastModified() > m_destFile.lastModified())) {
        m_fileMap.put(m_file.getAbsolutePath(), m_destFile.getAbsolutePath());
      } else {
        final String message = REZ.getString("copy.omit-uptodate.notice", m_file, m_destFile);
        getLogger().debug(message);
      }
    }
    final int size = m_filesets.size();
    for (int i = 0; i < size; i++) {
      final FileSet fileSet = ((FileSet) (m_filesets.get(i)));
      final DirectoryScanner scanner = ScannerUtil.getDirectoryScanner(fileSet);
      final File fromDir = fileSet.getDir();
      final String[] srcFiles = scanner.getIncludedFiles();
      final String[] srcDirs = scanner.getIncludedDirectories();
      scan(fromDir, m_destDir, srcFiles, srcDirs);
    }
    doFileOperations(m_fileMap, m_dirMap);
  }
}
