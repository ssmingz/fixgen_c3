class PlaceHold {
  public void setWorkingDirectory(File f) {
    _state.setWorkingDirectory(f);
    _notifier.projectWorkDirChanged();
    setProjectChanged(true);
    DrJava.getConfig().setSetting(LAST_INTERACTIONS_DIRECTORY, _state.getWorkingDirectory());
  }
}
