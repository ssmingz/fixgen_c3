class PlaceHold {
  public void addExtraClassPath(File f) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addExtraClassPath(f);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
