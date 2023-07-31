class PlaceHold {
  public boolean runTestSuite() {
    InterpreterJVMRemoteI remote = _accessInterpreterJVM();
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
