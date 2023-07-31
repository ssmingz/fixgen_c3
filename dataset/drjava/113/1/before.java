class PlaceHold {
  public void addExternalFilesClassPath(URL path) {
    if (!_enabled) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().addExternalFilesClassPath(path);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
