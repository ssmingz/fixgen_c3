class PlaceHold {
  public String getPrevious() {
    return getDataModel().getStateMachine().getPrevious(this, getDataModel());
  }
}
