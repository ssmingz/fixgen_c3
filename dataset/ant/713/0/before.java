class PlaceHold {
  protected void runExec(Execute exe) throws BuildException {
    try {
      Vector fileNames = new Vector();
      Vector baseDirs = new Vector();
      for (int i = 0; i < filesets.size(); i++) {
        FileSet fs = ((FileSet) (filesets.elementAt(i)));
        File base = fs.getDir(getProject());
        DirectoryScanner ds = fs.getDirectoryScanner(getProject());
        if (!"dir".equals(type)) {
          String[] s = getFiles(base, ds);
          for (int j = 0; j < s.length; j++) {
            fileNames.addElement(s[j]);
            baseDirs.addElement(base);
          }
        }
        if (!"file".equals(type)) {
          String[] s = getDirs(base, ds);
          for (int j = 0; j < s.length; j++) {
            fileNames.addElement(s[j]);
            baseDirs.addElement(base);
          }
        }
        if ((fileNames.size() == 0) && skipEmpty) {
          log(("Skipping fileset for directory " + base) + ". It is empty.", MSG_INFO);
          continue;
        }
        if (!parallel) {
          String[] s = new String[fileNames.size()];
          fileNames.copyInto(s);
          for (int j = 0; j < s.length; j++) {
            String[] command = getCommandline(s[j], base);
            log(Commandline.describeCommand(command), MSG_VERBOSE);
            exe.setCommandline(command);
            runExecute(exe);
          }
          fileNames.removeAllElements();
          baseDirs.removeAllElements();
        }
      }
      if (parallel && ((fileNames.size() > 0) || (!skipEmpty))) {
        String[] s = new String[fileNames.size()];
        fileNames.copyInto(s);
        File[] b = new File[baseDirs.size()];
        baseDirs.copyInto(b);
        String[] command = getCommandline(s, b);
        log(Commandline.describeCommand(command), MSG_VERBOSE);
        exe.setCommandline(command);
        runExecute(exe);
      }
    } catch (IOException e) {
      throw new BuildException("Execute failed: " + e, e, location);
    } finally {
      logFlush();
    }
  }
}
