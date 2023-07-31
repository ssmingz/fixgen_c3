class PlaceHold {
  public void setPrivateAccessible(boolean allow) {
    if (!_restart) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().setPrivateAccessible(allow);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
