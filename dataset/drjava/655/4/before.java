class PlaceHold {
  public void addProjectFilesClassPath(File f) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addProjectFilesClassPath(f);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
