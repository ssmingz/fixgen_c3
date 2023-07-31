class PlaceHold {
  public void execute() throws BuildException {
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
    final ChangeLogParser parser = new ChangeLogParser(userList);
    final RedirectingStreamHandler handler = new RedirectingStreamHandler(parser);
    final Execute exe = new Execute(handler);
    exe.setWorkingDirectory(m_basedir);
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
    final CVSEntry[] entrySet = parser.getEntrySetAsArray();
    final CVSEntry[] filteredEntrySet = filterEntrySet(entrySet);
    writeChangeLog(filteredEntrySet);
  }
}
