class PlaceHold {
  public boolean runTestSuite() {
    InterpreterJVMRemoteI remote = _state.value().interpreter(true);
    if (remote == null) {
      return false;
    }
    try {
      return remote.runTestSuite();
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return false;
    }
  }
}
