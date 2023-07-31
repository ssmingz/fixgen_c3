class PlaceHold {
  public void execute() throws TaskException {
    validateAttributes();
    if (m_propertyFile != null) {
      m_properties = getProperties(m_propertyFile);
    }
    validateReplacefilters();
    m_fileCount = 0;
    m_replaceCount = 0;
    if (m_src != null) {
      processFile(m_src);
    }
    if (m_dir != null) {
      DirectoryScanner ds = super.getDirectoryScanner(m_dir);
      String[] srcs = ds.getIncludedFiles();
      for (int i = 0; i < srcs.length; i++) {
        File file = new File(m_dir, srcs[i]);
        processFile(file);
      }
    }
    if (m_summary) {
      getContext()
          .info(((("Replaced " + m_replaceCount) + " occurrences in ") + m_fileCount) + " files.");
    }
  }
}
