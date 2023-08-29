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
    return null;
  }
}
