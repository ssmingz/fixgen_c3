class PlaceHold {
  public void execute() throws TaskException {
    validateAttributes();
    if (m_file != null) {
      if (m_file.exists()) {
        if (m_destFile == null) {
          m_destFile = new File(m_destDir, m_file.getName());
        }
        if (m_forceOverwrite || (m_file.lastModified() > m_destFile.lastModified())) {
          m_fileCopyMap.put(m_file.getAbsolutePath(), m_destFile.getAbsolutePath());
        } else {
          log(((m_file + " omitted as ") + m_destFile) + " is up to date.", MSG_VERBOSE);
        }
      } else {
        String message = ("Could not find file " + m_file.getAbsolutePath()) + " to copy.";
        getLogger().info(message);
        throw new TaskException(message);
      }
    }
    for (int i = 0; i < m_filesets.size(); i++) {
      final FileSet fileSet = ((FileSet) (m_filesets.elementAt(i)));
      final DirectoryScanner scanner = fileSet.getDirectoryScanner(getProject());
      final File fromDir = fileSet.getDir(getProject());
      final String[] srcFiles = scanner.getIncludedFiles();
      final String[] srcDirs = scanner.getIncludedDirectories();
      final boolean isEverythingIncluded = scanner.isEverythingIncluded();
      if ((isEverythingIncluded && (!m_flatten)) && (null == m_mapperElement)) {
        m_completeDirMap.put(fromDir, m_destDir);
      }
      scan(fromDir, m_destDir, srcFiles, srcDirs);
    }
    doFileOperations();
    if (null != m_destFile) {
      m_destDir = null;
    }
  }
}
