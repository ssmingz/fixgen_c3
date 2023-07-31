class PlaceHold {
  protected void runExec(final Execute exe) throws TaskException {
    try {
      final ArrayList fileNames = new ArrayList();
      final ArrayList baseDirs = new ArrayList();
      for (int i = 0; i < m_filesets.size(); i++) {
        final FileSet fs = ((FileSet) (m_filesets.get(i)));
        final File base = fs.getDir();
        final DirectoryScanner ds = ScannerUtil.getDirectoryScanner(fs);
        if (!"dir".equals(m_type)) {
          final String[] s = getFiles(base, ds);
          for (int j = 0; j < s.length; j++) {
            fileNames.add(s[j]);
            baseDirs.add(base);
          }
        }
        if (!"file".equals(m_type)) {
          final String[] s = getDirs(base, ds);
          for (int j = 0; j < s.length; j++) {
            fileNames.add(s[j]);
            baseDirs.add(base);
          }
        }
        if ((fileNames.size() == 0) && m_skipEmpty) {
          getLogger().info(("Skipping fileset for directory " + base) + ". It is empty.");
          continue;
        }
        if (!m_parallel) {
          String[] s = new String[fileNames.size()];
          s = ((String[]) (fileNames.toArray(s)));
          for (int j = 0; j < s.length; j++) {
            String[] command = getCommandline(s[j], base);
            getLogger().debug("Executing " + StringUtil.join(command, " "));
            exe.setCommandline(command);
            runExecute(exe);
          }
          fileNames.clear();
          baseDirs.clear();
        }
      }
      if (m_parallel && ((fileNames.size() > 0) || (!m_skipEmpty))) {
        String[] s = new String[fileNames.size()];
        s = ((String[]) (fileNames.toArray(s)));
        File[] b = new File[baseDirs.size()];
        b = ((File[]) (baseDirs.toArray(b)));
        String[] command = getCommandline(s, b);
        getLogger().debug("Executing " + StringUtil.join(command, " "));
        exe.setCommandline(command);
        runExecute(exe);
      }
    } catch (IOException e) {
      throw new TaskException("Execute failed: " + e, e);
    } finally {
      logFlush();
    }
  }
}
