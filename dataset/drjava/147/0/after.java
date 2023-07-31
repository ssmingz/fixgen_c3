class PlaceHold {
  public void setShowMessageOnResetFailure(boolean show) {
    if (!_restart) {
      return;
    }
    InterpreterJVMRemoteI slave = ensureInterpreterConnected();
    try {
      slave.setShowMessageOnResetFailure(show);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
