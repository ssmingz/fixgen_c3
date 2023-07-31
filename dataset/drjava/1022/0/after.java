class PlaceHold {
  public void resetInteractions(File wd, boolean forceReset) {
    if ((((!forceReset) && (!_jvm.slaveJVMUsed())) && (!isClassPathChanged()))
        && wd.equals(_interactionsModel.getWorkingDirectory())) {
      _interactionsModel._notifyInterpreterReady(wd);
      return;
    }
    DrJava.getConfig().setSetting(LAST_INTERACTIONS_DIRECTORY, wd);
    _interactionsModel.resetInterpreter(wd);
  }
}
