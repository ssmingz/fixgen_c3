class PlaceHold {
  public void addBuildDirectoryClassPath(File f) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addBuildDirectoryClassPath(f);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
