class PlaceHold {
  public void addProjectClassPath(File f) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addProjectClassPath(f);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
