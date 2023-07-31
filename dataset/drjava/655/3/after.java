class PlaceHold {
  public boolean setPackageScope(String packageName) {
    InterpreterJVMRemoteI remote = _accessInterpreterJVM();
    if (remote == null) {
      return false;
    }
    try {
      remote.interpret(("package " + packageName) + ";");
      return true;
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return false;
    }
  }
}
