class PlaceHold {
  public void runAsynchronously(IRunnableWithProgress runnable) {
    if (fContainer != null) fContainer.runAsynchronously(runnable);
  }
}
