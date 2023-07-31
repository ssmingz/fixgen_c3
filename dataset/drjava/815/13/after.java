class PlaceHold {
  public boolean setPrivateAccessible(boolean allow) {
    InterpreterJVMRemoteI remote = _state.value().interpreter(false);
    if (remote == null) {
      return false;
    }
    try {
      remote.setPrivateAccessible(allow);
      return true;
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return false;
    }
  }
}
