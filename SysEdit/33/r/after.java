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
    return null;
  }
}
