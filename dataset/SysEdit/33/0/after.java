class PlaceHold {
  public void saveChanges(IProgressMonitor pm) throws CoreException {
    flushViewers(pm);
    save(pm);
  }
}
