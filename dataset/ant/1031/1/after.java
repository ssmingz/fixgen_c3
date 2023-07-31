class PlaceHold {
  public void execute() throws TaskException {
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
    command.addArgument("log");
    if (null != m_start) {
      final SimpleDateFormat outputDate = new SimpleDateFormat("yyyy-MM-dd");
      final String dateRange = "-d >=" + outputDate.format(m_start);
      command.addArgument(dateRange);
    }
    final ChangeLogParser parser = new ChangeLogParser(userList);
    final Execute exe = new Execute();
    exe.setWorkingDirectory(m_basedir);
    exe.setCommandline(command);
    exe.setExecOutputHandler(parser);
    exe.execute(getContext());
    final CVSEntry[] entrySet = parser.getEntrySetAsArray();
    final CVSEntry[] filteredEntrySet = filterEntrySet(entrySet);
    writeChangeLog(filteredEntrySet);
  }
}
