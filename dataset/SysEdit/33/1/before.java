class PlaceHold {
  public Object getAdapter(Class adapter) {
    if (ICompareNavigator.class.equals(adapter) || CompareNavigator.class.equals(adapter)) {
      if (fNavigator == null)
        fNavigator =
            new CompareNavigator(
                new CompareViewerSwitchingPane[] {
                  fStructureInputPane, fStructurePane1, fStructurePane2, fContentInputPane
                });
      return fNavigator;
    }
    if (IFile.class.equals(adapter)) {
      IProgressMonitor pm = new NullProgressMonitor();
      try {
        flushViewer(fStructureInputPane, pm);
        flushViewer(fStructurePane1, pm);
        flushViewer(fStructurePane2, pm);
        flushViewer(fContentInputPane, pm);
      } catch (CoreException e) {
        CompareUIPlugin.log(e);
      }
      IFile[] files = (IFile[]) getAdapter(IFile[].class);
      if (files != null && files.length > 0) return files[0];
      return null;
    }
    return null;
  }
}
