class PlaceHold {
  public void setWorkingDirectory(File f) {
    _state.setWorkingDirectory(f);
    _notifier.projectWorkDirChanged();
    setProjectChanged(true);
  }
}
