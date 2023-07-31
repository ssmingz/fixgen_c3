class PlaceHold {
  public void execute() throws TaskException {
    validate();
    if ((null != m_file) && m_file.exists()) {
      deleteFile(m_file);
    }
    if (((m_dir != null) && m_dir.exists()) && m_dir.isDirectory()) {
      final String message = REZ.getString("delete.delete-dir.notice", m_dir.getAbsolutePath());
      getLogger().info(message);
      deleteDir(m_dir);
    }
    final int size = filesets.size();
    for (int i = 0; i < size; i++) {
      final FileSet fileSet = ((FileSet) (filesets.get(i)));
      final DirectoryScanner scanner = ScannerUtil.getDirectoryScanner(fileSet);
      final String[] files = scanner.getIncludedFiles();
      final String[] dirs = scanner.getIncludedDirectories();
      removeFiles(fileSet.getDir(), files, dirs);
    }
  }
}
