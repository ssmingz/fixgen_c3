class PlaceHold {
  public void textChanged(TextEvent event) {
    Iterator e = fActions.values().iterator();
    while (e.hasNext()) {
      MergeViewerAction action = ((MergeViewerAction) (e.next()));
      if (action.isContentDependent()) action.update();
    }
  }
}
