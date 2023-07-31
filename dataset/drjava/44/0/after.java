class PlaceHold {
  public void addRegistrationListener(RegistrationListener list) {
    synchronized (_regListeners) {
      _regListeners.add(list);
    }
  }
}
