class PlaceHold {
  public void classFileError(ClassFileError e) {
    try {
      _mainJVM.classFileError(e);
    } catch (RemoteException re) {
      error.log(re);
    }
  }
}
