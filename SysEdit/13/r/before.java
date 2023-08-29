class PlaceHold {
  public void selectionChanged(SelectionChangedEvent event) {
    Iterator e = fActions.values().iterator();
    while (e.hasNext()) {
      MergeViewerAction action = (MergeViewerAction) e.next();
      if (action.isSelectionDependent()) action.update();
    }
  }
}
