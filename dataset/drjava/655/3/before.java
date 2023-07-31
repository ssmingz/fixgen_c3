class PlaceHold {
  public void setPackageScope(String packageName) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.interpret(("package " + packageName) + ";");
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
