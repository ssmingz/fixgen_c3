class PlaceHold {
  public boolean addExternalFilesClassPath(File f) {
    InterpreterJVMRemoteI remote = _state.value().interpreter(false);
    if (remote == null) {
      return false;
    }
    try {
      remote.addExternalFilesClassPath(f);
      return true;
    } catch (RemoteException e) {
      _handleRemoteException(e);
      return false;
    }
  }
}
