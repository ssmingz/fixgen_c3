class PlaceHold {
  public void selectionChanged(SelectionChangedEvent event) {
    Iterator e = fActions.values().iterator();
    while (e.hasNext()) {
      Object next = e.next();
      if (next instanceof MergeViewerAction) {
        MergeViewerAction action = (MergeViewerAction) next;
        if (action.isSelectionDependent()) action.update();
      }
    }
  }
}
