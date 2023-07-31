class PlaceHold {
  public void setPrivateAccessible(boolean allow) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.setPrivateAccessible(allow);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
