class PlaceHold {
  public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable)
      throws InvocationTargetException, InterruptedException {
    if (fContainer != null) fContainer.run(fork, cancelable, runnable);
  }
}
