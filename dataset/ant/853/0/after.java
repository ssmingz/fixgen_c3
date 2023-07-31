class PlaceHold {
  public void setErrorFilterChains(Vector errorFilterChains) {
    synchronized (errMutex) {
      this.errorFilterChains = errorFilterChains;
    }
  }
}
