class PlaceHold {
  public boolean addBuildDirectoryClassPath(File f) {
    InterpreterJVMRemoteI remote = _accessInterpreterJVM();
    if (remote == null) {
      return false;
    }
    try {
      remote.addBuildDirectoryClassPath(f);
      return true;
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return false;
    }
  }
}
