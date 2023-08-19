class PlaceHold {
  public void addCompareInputChangeListener(
      ICompareInput input, ICompareInputChangeListener listener) {
    if (fContainer == null) {
      input.addCompareInputChangeListener(listener);
    } else {
      fContainer.addCompareInputChangeListener(input, listener);
    }
  }
}
