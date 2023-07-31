class PlaceHold {
  protected final BigInteger popValue() {
    currentValue = valueStack.pop();
    return currentValue;
  }
}
