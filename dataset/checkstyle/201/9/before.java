class PlaceHold {
  protected final void fireErrors(String fileName) {
    final TreeSet<LocalizedMessage> errors = getMessageCollector().getMessages();
    getMessageCollector().reset();
    getMessageDispatcher().fireErrors(fileName, errors);
  }
}
