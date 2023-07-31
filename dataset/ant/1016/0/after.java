class PlaceHold {
  public void setAppendProperties(boolean appendProperties) {
    synchronized (outMutex) {
      this.appendProperties = appendProperties;
    }
  }
}
