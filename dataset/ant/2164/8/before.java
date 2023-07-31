class PlaceHold {
  private void touch() throws TaskException {
    if (m_millis < 0) {
      m_millis = System.currentTimeMillis();
    }
    if (m_file != null) {
      if (!m_file.exists()) {
        getLogger().info("Creating " + m_file);
        try {
          FileOutputStream fos = new FileOutputStream(m_file);
          fos.write(new byte[0]);
          fos.close();
        } catch (final IOException ioe) {
          final String message = "Could not create " + m_file;
          throw new TaskException(message, ioe);
        }
      }
      touch(m_file);
    }
    final int size = m_filesets.size();
    for (int i = 0; i < size; i++) {
      final FileSet fs = ((FileSet) (m_filesets.get(i)));
      final DirectoryScanner ds = fs.getDirectoryScanner();
      final File fromDir = fs.getDir();
      final String[] srcFiles = ds.getIncludedFiles();
      final String[] srcDirs = ds.getIncludedDirectories();
      for (int j = 0; j < srcFiles.length; j++) {
        touch(new File(fromDir, srcFiles[j]));
      }
      for (int j = 0; j < srcDirs.length; j++) {
        touch(new File(fromDir, srcDirs[j]));
      }
    }
  }
}
