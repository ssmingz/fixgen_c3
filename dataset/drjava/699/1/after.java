class PlaceHold {
  private void _insertNewQuote(String quote) {
    String insert = _getQuoteType(quote);
    _cursor.insertNewBrace(insert);
    _cursor.prev();
    _updateBasedOnCurrentState();
    _cursor.next();
    _cursor.setBlockOffset(0);
  }
}
