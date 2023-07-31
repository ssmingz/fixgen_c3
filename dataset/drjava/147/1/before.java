class PlaceHold {
  public void addJavaInterpreter(String name) {
    if (!_restart) {
      return;
    }
    ensureInterpreterConnected();
    try {
      _interpreterJVM().addJavaInterpreter(name);
    } catch (RemoteException re) {
      _threwException(re);
    }
  }
}
