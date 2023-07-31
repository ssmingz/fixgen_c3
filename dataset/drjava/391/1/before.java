class PlaceHold {
  public void addExtraClassPath(URL path) {
    if (!_restart) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().addExtraClassPath(path.toString());
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
