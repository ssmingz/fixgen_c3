class PlaceHold {
  public Option<Iterable<File>> getClassPath() {
    InterpreterJVMRemoteI remote = _accessInterpreterJVM();
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
