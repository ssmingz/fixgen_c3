class PlaceHold {
  public void textChanged(TextEvent event) {
    Iterator e = fActions.values().iterator();
    while (e.hasNext()) {
      Object next = e.next();
      if (next instanceof MergeViewerAction) {
        MergeViewerAction action = ((MergeViewerAction) (next));
        if (action.isContentDependent()) action.update();
      }
    }
  }
}
