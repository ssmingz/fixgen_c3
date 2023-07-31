class PlaceHold {
  public void setInput(File[] input) {
    synchronized (inMutex) {
      if (input == null) {
        this.input = null;
      } else {
        this.input = input.clone();
      }
    }
  }
}
