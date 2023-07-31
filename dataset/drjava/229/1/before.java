class PlaceHold {
  public void addProjectClassPath(URL path) {
    if (!_enabled) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().addProjectClassPath(path);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
