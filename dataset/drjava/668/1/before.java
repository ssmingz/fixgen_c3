class PlaceHold {
  public boolean setToDefaultInterpreter() {
    if (!_restart) {
      return false;
    }
    ensureInterpreterConnected();
    try {
      boolean result = _interpreterJVM().setToDefaultInterpreter();
      _currentInterpreterName = DEFAULT_INTERPRETER_NAME;
      return result;
    } catch (ConnectIOException ce) {
      _log.log((this + "could not connect to the interpreterJVM after killing it.  Threw ") + ce);
      return false;
    } catch (RemoteException re) {
      _threwException(re);
      return false;
    }
  }
}
