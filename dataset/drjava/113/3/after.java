class PlaceHold {
  public void addBuildDirectoryClassPath(URL path) {
    if (!_enabled) {
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
