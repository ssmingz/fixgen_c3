class PlaceHold {
  public void addExternalFilesClassPath(URL path) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addExternalFilesClassPath(path.toString());
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
