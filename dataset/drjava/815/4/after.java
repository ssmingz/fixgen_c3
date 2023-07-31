class PlaceHold {
  public Option<Iterable<File>> getClassPath() {
    InterpreterJVMRemoteI remote = _state.value().interpreter(false);
    if (remote == null) {
      return Option.none();
    }
    try {
      return Option.some(remote.getClassPath());
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return Option.none();
    }
  }
}
