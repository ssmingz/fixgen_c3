class PlaceHold {
  public IMoney addMoney(Money m) {
    return new MoneyBag(m, this).simplify();
  }
}
