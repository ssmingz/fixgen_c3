class PlaceHold {
  public void saveChanges(IProgressMonitor pm) throws org.eclipse.compare.CoreException {
    flushViewers(pm);
    save(pm);
  }
}
