class PlaceHold {
  public Object getAdapter(Class adapter) {
    if (org.eclipse.compare.ICompareNavigator.class.equals(adapter)
        || org.eclipse.compare.CompareNavigator.class.equals(adapter)) {
      if (fNavigator == null)
        fNavigator =
            new CompareNavigator(
                new CompareViewerSwitchingPane[] {
                  fStructureInputPane, fStructurePane1, fStructurePane2, fContentInputPane
                });

      return fNavigator;
    }
    if (org.eclipse.core.resources.IFile.class.equals(adapter)) {
      IProgressMonitor pm = new NullProgressMonitor();
      // flush changes in any dirty viewer
      try {
        flushViewer(fStructureInputPane, pm);
        flushViewer(fStructurePane1, pm);
        flushViewer(fStructurePane2, pm);
        flushViewer(fContentInputPane, pm);
      } catch (CoreException e) {
        CompareUIPlugin.log(e);
      }
      org.eclipse.core.resources.IFile[] files =
          ((org.eclipse.core.resources.IFile[])
              (getAdapter(org.eclipse.core.resources.IFile[].class)));
      if ((files != null) && (files.length > 0)) return files[0];
      // can only return one: limitation on IDE.saveAllEditors; see #64617

      return null;
    }
    return null;
  }
}
