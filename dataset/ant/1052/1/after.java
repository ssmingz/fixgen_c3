class PlaceHold {
  public void execute() throws BuildException {
    File savedDir = m_dir;
    try {
      validate();
      final Properties userList = new Properties();
      loadUserlist(userList);
      for (Enumeration e = m_cvsUsers.elements(); e.hasMoreElements(); ) {
        final CvsUser user = ((CvsUser) (e.nextElement()));
        user.validate();
        userList.put(user.getUserID(), user.getDisplayname());
      }
      final Commandline command = new Commandline();
      command.setExecutable("cvs");
      command.createArgument().setValue("log");
      if (null != m_start) {
        final SimpleDateFormat outputDate = new SimpleDateFormat("yyyy-MM-dd");
        final String dateRange = "-d >=" + outputDate.format(m_start);
        command.createArgument().setValue(dateRange);
      }
      if (!m_filesets.isEmpty()) {
        final Enumeration e = m_filesets.elements();
        while (e.hasMoreElements()) {
          final FileSet fileSet = ((FileSet) (e.nextElement()));
          final DirectoryScanner scanner = fileSet.getDirectoryScanner(getProject());
          final String[] files = scanner.getIncludedFiles();
          for (int i = 0; i < files.length; i++) {
            command.createArgument().setValue(files[i]);
          }
        }
      }
      final ChangeLogParser parser = new ChangeLogParser();
      final RedirectingStreamHandler handler = new RedirectingStreamHandler(parser);
      log(("ChangeLog command: [" + command.toString()) + "]", MSG_VERBOSE);
      final Execute exe = new Execute(handler);
      exe.setWorkingDirectory(m_dir);
      exe.setCommandline(command.getCommandline());
      exe.setAntRun(getProject());
      try {
        final int resultCode = exe.execute();
        if (0 != resultCode) {
          throw new BuildException("Error running cvs log");
        }
      } catch (final IOException ioe) {
        throw new BuildException(ioe.toString());
      }
      final String errors = handler.getErrors();
      if (null != errors) {
        log(errors, MSG_ERR);
      }
      final CVSEntry[] entrySet = parser.getEntrySetAsArray();
      final CVSEntry[] filteredEntrySet = filterEntrySet(entrySet);
      replaceAuthorIdWithName(userList, filteredEntrySet);
      writeChangeLog(filteredEntrySet);
    } finally {
      m_dir = savedDir;
    }
  }
}
