class PlaceHold {
  public void addProjectFilesClassPath(URL path) {
    if (!_restart) {
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
