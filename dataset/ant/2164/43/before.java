class PlaceHold {
  public void execute() throws TaskException {
    if (((m_file == null) && (m_dir == null)) && (filesets.size() == 0)) {
      final String message =
          "At least one of the file or dir attributes, " + "or a fileset element, must be set.";
      throw new TaskException(message);
    }
    if (null != m_file) {
      if (m_file.exists()) {
        if (m_file.isDirectory()) {
          final String message =
              ("Directory " + m_file.getAbsolutePath())
                  + " cannot be removed using the file attribute.  Use dir instead.";
          getLogger().info(message);
        } else {
          getLogger().info("Deleting: " + m_file.getAbsolutePath());
          if (!m_file.delete()) {
            final String message = "Unable to delete file " + m_file.getAbsolutePath();
            throw new TaskException(message);
          }
        }
      } else {
        final String message = ("Could not find file " + m_file.getAbsolutePath()) + " to delete.";
        getLogger().debug(message);
      }
    }
    if (((m_dir != null) && m_dir.exists()) && m_dir.isDirectory()) {
      getLogger().info("Deleting directory " + m_dir.getAbsolutePath());
      removeDir(m_dir);
    }
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.get(i)));
      try {
        DirectoryScanner ds = fs.getDirectoryScanner();
        String[] files = ds.getIncludedFiles();
        String[] dirs = ds.getIncludedDirectories();
        removeFiles(fs.getDir(), files, dirs);
      } catch (TaskException be) {
        throw be;
      }
    }
  }
}
