class PlaceHold {
  public void addBuildDirectoryClassPath(URL path) {
    if (!_restart) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().addBuildDirectoryClassPath(path.toString());
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
