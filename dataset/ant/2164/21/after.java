class PlaceHold {
  private void addIncludes() throws TaskException {
    addToIncludeFileMap(m_file);
    final int size = m_filesets.size();
    for (int i = 0; i < size; i++) {
      final FileSet fileSet = ((FileSet) (m_filesets.get(i)));
      final DirectoryScanner scanner = ScannerUtil.getDirectoryScanner(fileSet);
      final String[] srcFiles = scanner.getIncludedFiles();
      for (int j = 0; j < srcFiles.length; j++) {
        final File src = new File(fileSet.getDir(), srcFiles[j]);
        addToIncludeFileMap(src);
      }
    }
  }
}
