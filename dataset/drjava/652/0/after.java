class PlaceHold {
  public void addProjectFilesClassPath(URL path) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addProjectFilesClassPath(path.toString());
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
