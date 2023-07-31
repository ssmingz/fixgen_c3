class PlaceHold {
  public boolean addExtraClassPath(File f) {
    InterpreterJVMRemoteI remote = _accessInterpreterJVM();
    if (remote == null) {
      return false;
    }
    try {
      remote.addExtraClassPath(f);
      return true;
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return false;
    }
  }
}
