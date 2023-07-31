class PlaceHold {
  public void setLogError(boolean logError) {
    synchronized (errMutex) {
      this.logError = logError;
    }
  }
}
