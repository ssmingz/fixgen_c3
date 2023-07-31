class PlaceHold {
  public Option<List<String>> findTestClasses(List<String> classNames, List<File> files) {
    InterpreterJVMRemoteI remote = _accessInterpreterJVM();
    if (remote == null) {
      return Option.none();
    }
    try {
      return Option.some(remote.findTestClasses(classNames, files));
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return Option.none();
    }
  }
}
