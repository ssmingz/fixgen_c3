class PlaceHold {
  public Transaction createTransaction() {
    Transaction t = new Transaction();
    transactions.addElement(t);
    return t;
  }
}
