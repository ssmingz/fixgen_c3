class PlaceHold {
  public void execute() throws BuildException {
    File savedDir = inputDir;
    try {
      validate();
      final Properties userList = new Properties();
      loadUserlist(userList);
      final int size = cvsUsers.size();
      for (int i = 0; i < size; i++) {
        final CvsUser user = ((CvsUser) (cvsUsers.get(i)));
        user.validate();
        userList.put(user.getUserID(), user.getDisplayname());
      }
      if (!remote) {
        setCommand("log");
        if (getTag() != null) {
          CvsVersion myCvsVersion = new CvsVersion();
          myCvsVersion.setProject(getProject());
          myCvsVersion.setTaskName("cvsversion");
          myCvsVersion.setCvsRoot(getCvsRoot());
          myCvsVersion.setCvsRsh(getCvsRsh());
          myCvsVersion.setPassfile(getPassFile());
          myCvsVersion.setDest(inputDir);
          myCvsVersion.execute();
          if (myCvsVersion.supportsCvsLogWithSOption()) {
            addCommandArgument("-S");
          }
        }
      } else {
        setCommand("");
        addCommandArgument("rlog");
        addCommandArgument("-S");
        addCommandArgument("-N");
      }
      if ((null != startTag) || (null != endTag)) {
        String startValue = (startTag == null) ? "" : startTag;
        String endValue = (endTag == null) ? "" : endTag;
        addCommandArgument((("-r" + startValue) + "::") + endValue);
      } else if (null != startDate) {
        final SimpleDateFormat outputDate = new SimpleDateFormat("yyyy-MM-dd");
        final String dateRange = ">=" + outputDate.format(startDate);
        addCommandArgument("-d");
        addCommandArgument(dateRange);
      }
      if (!filesets.isEmpty()) {
        final Enumeration e = filesets.elements();
        while (e.hasMoreElements()) {
          final FileSet fileSet = ((FileSet) (e.nextElement()));
          final DirectoryScanner scanner = fileSet.getDirectoryScanner(getProject());
          final String[] files = scanner.getIncludedFiles();
          for (int i = 0; i < files.length; i++) {
            addCommandArgument(files[i]);
          }
        }
      }
      final ChangeLogParser parser = new ChangeLogParser(remote, getPackage(), getModules());
      final RedirectingStreamHandler handler = new RedirectingStreamHandler(parser);
      log(getCommand(), MSG_VERBOSE);
      setDest(inputDir);
      setExecuteStreamHandler(handler);
      try {
        super.execute();
      } finally {
        final String errors = handler.getErrors();
        if (null != errors) {
          log(errors, MSG_ERR);
        }
      }
      final CVSEntry[] entrySet = parser.getEntrySetAsArray();
      final CVSEntry[] filteredEntrySet = filterEntrySet(entrySet);
      replaceAuthorIdWithName(userList, filteredEntrySet);
      writeChangeLog(filteredEntrySet);
    } finally {
      inputDir = savedDir;
    }
  }
}
