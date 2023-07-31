class PlaceHold {
  public Transaction createTransaction() {
    Transaction t = new Transaction();
    transactions.add(t);
    return t;
  }
}
