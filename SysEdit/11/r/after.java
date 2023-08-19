class PlaceHold {
  public void removeSelectionChangedListener(ISelectionChangedListener listener) {
    if (fStructurePane != null) fStructurePane.removeSelectionChangedListener(listener);
  }
}
