class PlaceHold {
  public void resetInteractions(File wd, boolean forceReset) {
    assert _interactionsModel._pane != null;
    debug.logStart();
    File workDir = _interactionsModel.getWorkingDirectory();
    if (wd == null) {
      wd = workDir;
    }
    if ((((!forceReset) && (!_jvm.slaveJVMUsed())) && (!isClassPathChanged()))
        && wd.equals(workDir)) {
      debug.log();
      _interactionsModel._notifyInterpreterReady(wd);
      debug.logEnd();
      return;
    }
    debug.log();
    DrJava.getConfig().setSetting(LAST_INTERACTIONS_DIRECTORY, wd);
    _interactionsModel.resetInterpreter(wd);
    debug.logEnd();
  }
}
