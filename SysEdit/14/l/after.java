class PlaceHold {
  public void runAsynchronously(org.eclipse.jface.operation.IRunnableWithProgress runnable) {
    if (fContainer != null) fContainer.runAsynchronously(runnable);
  }
}
