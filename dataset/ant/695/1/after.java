class PlaceHold {
  protected void fireEvent(TestRunEvent evt) {
    try {
      messenger.writeEvent(evt);
    } catch (IOException e) {
      log(e);
    }
  }
}
