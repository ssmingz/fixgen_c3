class PlaceHold {
  public void execute() throws TaskException {
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
      throw new TaskException("Missing Sourcepath required argument");
    }
    int exitValue = JDependTask.ERRORS;
    boolean wasKilled = false;
    if (!getFork()) {
      exitValue = executeInVM(commandline);
    } else {
      exitValue = executeAsForked(commandline);
    }
    boolean errorOccurred = exitValue == JDependTask.ERRORS;
    if (errorOccurred) {
      if (getHaltonerror()) {
        throw new TaskException("JDepend failed");
      } else {
        getLogger().error("JDepend FAILED");
      }
    }
  }
}
