class PlaceHold {
  public void addJavaInterpreter(String name) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addJavaInterpreter(name);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
