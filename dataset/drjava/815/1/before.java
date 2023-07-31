class PlaceHold {
  public boolean addProjectFilesClassPath(File f) {
    InterpreterJVMRemoteI remote = _accessInterpreterJVM();
    if (remote == null) {
      return false;
    }
    try {
      remote.addProjectFilesClassPath(f);
      return true;
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return false;
    }
  }
}
