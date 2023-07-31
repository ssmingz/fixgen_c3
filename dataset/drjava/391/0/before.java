class PlaceHold {
  public void addExternalFilesClassPath(URL path) {
    if (!_restart) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().addExternalFilesClassPath(path.toString());
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
