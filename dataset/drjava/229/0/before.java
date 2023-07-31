class PlaceHold {
  public void addExtraClassPath(URL path) {
    if (!_enabled) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().addExtraClassPath(path);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
