class PlaceHold {
  public void execute() throws TaskException {
    if (null == m_srcDir) {
      m_srcDir = getBaseDirectory();
    }
    final DirectoryScanner scanner = super.getDirectoryScanner(m_srcDir);
    final String[] dependencies = scanner.getIncludedFiles();
    final String baseDir = scanner.getBasedir().toString();
    final String message =
        (("assembling " + dependencies.length) + " file") + (dependencies.length == 1 ? "" : "s");
    getContext().info(message);
    for (int i = 0; i < dependencies.length; i++) {
      final String targetFile = (baseDir + File.separator) + dependencies[i];
      executeOneFile(targetFile);
    }
  }
}
