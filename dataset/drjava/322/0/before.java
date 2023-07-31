class PlaceHold {
  public void addInterpreter(String name) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.addInterpreter(name);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
