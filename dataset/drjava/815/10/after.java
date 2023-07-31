class PlaceHold {
  public Option<String> getVariableToString(String var) {
    InterpreterJVMRemoteI remote = _state.value().interpreter(false);
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
