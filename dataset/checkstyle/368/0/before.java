class PlaceHold {
  private void leaveAddingConditional() {
    setCurrentValue(getCurrentValue().subtract(BigInteger.ONE).add(popValue()));
  }
}
