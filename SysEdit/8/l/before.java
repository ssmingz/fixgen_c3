class PlaceHold {
  public org.eclipse.compare.IActionBars getActionBars() {
    if (fContainer == null) {
      // The old way to find the action bars
      return Utilities.findActionBars(fComposite);
    }
    return fContainer.getActionBars();
  }
}
