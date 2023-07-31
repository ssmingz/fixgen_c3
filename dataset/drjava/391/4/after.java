class PlaceHold {
  public void addProjectClassPath(URL path) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addProjectClassPath(path.toString());
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
