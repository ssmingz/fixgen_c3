class PlaceHold {
  protected ArrayList getFileList() throws TaskException {
    ArrayList files = new ArrayList();
    if (m_filesets.size() == 0) {
      appendFiles(files, super.getDirectoryScanner(m_baseDir));
    } else {
      for (int i = 0; i < m_filesets.size(); i++) {
        FileSet fs = ((FileSet) (m_filesets.get(i)));
        if (fs != null) {
          appendFiles(files, ScannerUtil.getDirectoryScanner(fs));
        }
      }
    }
    return files;
  }
}
