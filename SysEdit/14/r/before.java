class PlaceHold {
  public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    try {
      saveChanges(monitor);
    } catch (CoreException e) {
      throw new InvocationTargetException(e);
    }
  }
}
