class PlaceHold {
  public String getNext() {
    return getDataModel().getStateMachine().getNext(this, getDataModel());
  }
}
