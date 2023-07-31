class PlaceHold {
  public void execute() throws BuildException {
    validate();
    addCommandArgument("rdiff");
    addCommandArgument("-s");
    if (mystartTag != null) {
      addCommandArgument("-r");
      addCommandArgument(mystartTag);
    } else {
      addCommandArgument("-D");
      addCommandArgument(mystartDate);
    }
    if (myendTag != null) {
      addCommandArgument("-r");
      addCommandArgument(myendTag);
    } else {
      addCommandArgument("-D");
      addCommandArgument(myendDate);
    }
    StringTokenizer myTokenizer = new StringTokenizer(mypackage);
    while (myTokenizer.hasMoreTokens()) {
      addCommandArgument(myTokenizer.nextToken());
    }
    setCommand("");
    File tmpFile = null;
    try {
      tmpFile = FILE_UTILS.createTempFile("cvstagdiff", ".log", null);
      tmpFile.deleteOnExit();
      setOutput(tmpFile);
      super.execute();
      CvsTagEntry[] entries = parseRDiff(tmpFile);
      writeTagDiff(entries);
    } finally {
      if (tmpFile != null) {
        tmpFile.delete();
      }
    }
  }
}
