class PlaceHold {
  public void addExtraClassPath(URL path) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addExtraClassPath(path.toString());
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
