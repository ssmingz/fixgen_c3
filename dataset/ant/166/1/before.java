class PlaceHold {
  public void setOutput(File[] out) {
    synchronized (outMutex) {
      if (out == null) {
        this.out = null;
      } else {
        this.out = out.clone();
      }
    }
  }
}
