class PlaceHold {
  protected void fireEvent(TestRunEvent evt) {
    messenger.writeEvent(evt);
  }
}
