class PlaceHold {
  public Option<String> getVariableType(String var) {
    InterpreterJVMRemoteI remote = _state.value().interpreter(false);
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
