class PlaceHold {
  public void execute() throws BuildException {
    CommandlineJava commandline = new CommandlineJava();
    if ("text".equals(format)) {
      commandline.setClassname("jdepend.textui.JDepend");
    } else if ("xml".equals(format)) {
      commandline.setClassname("jdepend.xmlui.JDepend");
    }
    if (_jvm != null) {
      commandline.setVm(_jvm);
    }
    if (getSourcespath() == null) {
      throw new BuildException("Missing Sourcepath required argument");
    }
    int exitValue = JDependTask.ERRORS;
    if (!getFork()) {
      exitValue = executeInVM(commandline);
    } else {
      ExecuteWatchdog watchdog = createWatchdog();
      exitValue = executeAsForked(commandline, watchdog);
      if (watchdog != null) {}
    }
    boolean errorOccurred = exitValue == JDependTask.ERRORS;
    if (errorOccurred) {
      if (getHaltonerror()) {
        throw new BuildException("JDepend failed", getLocation());
      } else {
        log("JDepend FAILED", MSG_ERR);
      }
    }
  }
}
