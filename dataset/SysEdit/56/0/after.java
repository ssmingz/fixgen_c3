class PlaceHold {
  public Boolean m6() {
    boolean actionBars = fContainer.isLeftDirty();
    if ((actionBars == false) && (!fContainerProvided)) {
      return C.findActionBars();
    }
    return actionBars;
  }
}
