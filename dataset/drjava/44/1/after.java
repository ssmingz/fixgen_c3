class PlaceHold {
  public void removeRegistrationListener(RegistrationListener list) {
    synchronized (_regListeners) {
      _regListeners.remove(list);
    }
  }
}
