class PlaceHold {
  protected void validateAttributes() throws TaskException {
    if ((m_file == null) && (m_filesets.size() == 0)) {
      throw new TaskException("Specify at least one source - a file or a fileset.");
    }
    if ((m_destFile != null) && (m_destDir != null)) {
      throw new TaskException("Only one of tofile and todir may be set.");
    }
    if ((m_destFile == null) && (m_destDir == null)) {
      throw new TaskException("One of tofile or todir must be set.");
    }
    if (((m_file != null) && m_file.exists()) && m_file.isDirectory()) {
      throw new TaskException("Use a fileset to copy directories.");
    }
    if ((m_destFile != null) && (m_filesets.size() > 0)) {
      if (m_filesets.size() > 1) {
        throw new TaskException("Cannot concatenate multiple files into a single file.");
      } else {
        FileSet fs = ((FileSet) (m_filesets.get(0)));
        DirectoryScanner ds = fs.getDirectoryScanner();
        String[] srcFiles = ds.getIncludedFiles();
        if (srcFiles.length > 0) {
          if (m_file == null) {
            m_file = new File(srcFiles[0]);
            m_filesets.remove(0);
          } else {
            throw new TaskException("Cannot concatenate multiple files into a single file.");
          }
        } else {
          throw new TaskException("Cannot perform operation from directory to file.");
        }
      }
    }
    if (m_destFile != null) {
      m_destDir = new File(m_destFile.getParent());
    }
  }
}
