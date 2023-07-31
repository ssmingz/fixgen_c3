class PlaceHold {
  public Option<String> getVariableType(String var) {
    InterpreterJVMRemoteI remote = _accessInterpreterJVM();
    if (remote == null) {
      return Option.none();
    }
    try {
      return Option.some(remote.getVariableType(var));
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return Option.none();
    }
  }
}
