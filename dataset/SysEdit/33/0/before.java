class PlaceHold {
  public void saveChanges(IProgressMonitor pm) throws CoreException {
    flushViewer(fStructureInputPane, pm);
    flushViewer(fStructurePane1, pm);
    flushViewer(fStructurePane2, pm);
    flushViewer(fContentInputPane, pm);
    save(pm);
  }
}
