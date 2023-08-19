class PlaceHold {
  public void saveChanges(IProgressMonitor pm) throws org.eclipse.compare.CoreException {
    // flush changes in any dirty viewer
    flushViewer(fStructureInputPane, pm);
    flushViewer(fStructurePane1, pm);
    flushViewer(fStructurePane2, pm);
    flushViewer(fContentInputPane, pm);
    save(pm);
  }
}
