class PlaceHold {
  protected String getInteractionsText() throws DocumentAdapterException {
    ConsoleInterface doc = _model.getInteractionsDocument();
    return doc.getDocText(0, doc.getLength());
  }
}
