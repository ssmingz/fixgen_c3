class PlaceHold {
  public IMoney addMoneyBag(MoneyBag s) {
    return new MoneyBag(s, this).simplify();
  }
}
