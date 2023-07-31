class PlaceHold {
  public void addExternalFilesClassPath(File f) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addExternalFilesClassPath(f);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
