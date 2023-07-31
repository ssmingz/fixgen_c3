class PlaceHold {
  public void addProjectFilesClassPath(URL path) {
    if (!_enabled) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().addProjectFilesClassPath(path.toString());
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
