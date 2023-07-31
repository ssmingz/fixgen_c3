class PlaceHold {
  public void addProjectClassPath(URL path) {
    if (!_restart) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().addProjectClassPath(path.toString());
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
