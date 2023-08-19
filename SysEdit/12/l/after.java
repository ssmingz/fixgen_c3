class PlaceHold {
  public void removeCompareInputChangeListener(
      ICompareInput input, ICompareInputChangeListener listener) {
    if (fContainer == null) {
      input.removeCompareInputChangeListener(listener);
    } else {
      fContainer.removeCompareInputChangeListener(input, listener);
    }
  }
}
