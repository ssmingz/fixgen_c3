class PlaceHold {
  public boolean addInterpreter(String name) {
    InterpreterJVMRemoteI remote = _state.value().interpreter(false);
    if (remote == null) {
      return false;
    }
    try {
      remote.addInterpreter(name);
      return true;
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return false;
    }
  }
}
