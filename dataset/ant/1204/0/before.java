class PlaceHold {
  public void setError(File[] error) {
    synchronized (errMutex) {
      if (error == null) {
        this.error = null;
      } else {
        this.error = error.clone();
      }
    }
  }
}
