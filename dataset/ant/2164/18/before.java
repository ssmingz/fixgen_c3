class PlaceHold {
  public void execute() throws TaskException {
    validate();
    if (null != m_jar) {
      doOneJar(m_jar, m_signedjar);
    } else {
      for (int i = 0; i < m_filesets.size(); i++) {
        final FileSet fileSet = ((FileSet) (m_filesets.get(i)));
        final DirectoryScanner scanner = fileSet.getDirectoryScanner();
        final String[] jarFiles = scanner.getIncludedFiles();
        for (int j = 0; j < jarFiles.length; j++) {
          final File file = new File(fileSet.getDir(), jarFiles[j]);
          doOneJar(file, null);
        }
      }
    }
  }
}
