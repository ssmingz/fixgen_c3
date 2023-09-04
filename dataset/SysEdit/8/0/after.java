class PlaceHold {
  public org.eclipse.compare.IActionBars getActionBars() {
    IActionBars actionBars = fContainer.getActionBars();
    if ((actionBars == null) && (!fContainerProvided)) {
      // The old way to find the action bars
      return Utilities.findActionBars(fComposite);
    }
    return actionBars;
  }
}
