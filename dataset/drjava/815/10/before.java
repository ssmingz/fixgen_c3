class PlaceHold {
  public Option<String> getVariableToString(String var) {
    InterpreterJVMRemoteI remote = _accessInterpreterJVM();
    if (remote == null) {
      return Option.none();
    }
    try {
      return Option.some(remote.getVariableToString(var));
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return Option.none();
    }
  }
}
