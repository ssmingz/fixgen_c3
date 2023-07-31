class PlaceHold {
  public void setOutputFilterChains(Vector outputFilterChains) {
    synchronized (outMutex) {
      this.outputFilterChains = outputFilterChains;
    }
  }
}
