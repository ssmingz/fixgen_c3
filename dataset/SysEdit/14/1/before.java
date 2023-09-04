class PlaceHold {
  public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable)
      throws InvocationTargetException, InterruptedException {
    fContainer.run(fork, cancelable, runnable);
  }
}
