class PlaceHold {
  public void addBuildDirectoryClassPath(URL path) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addBuildDirectoryClassPath(path.toString());
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
