class PlaceHold {
  public void setShowMessageOnResetFailure(boolean show) {
    if (!_restart) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().setShowMessageOnResetFailure(show);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
