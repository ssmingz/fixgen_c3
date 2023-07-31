class PlaceHold {
  public boolean addProjectClassPath(File f) {
    InterpreterJVMRemoteI remote = _accessInterpreterJVM();
    if (remote == null) {
      return false;
    }
    try {
      remote.addProjectClassPath(f);
      return true;
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return false;
    }
  }
}
