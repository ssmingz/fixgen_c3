class PlaceHold {
  protected final BigInteger popValue() {
    mCurrentValue = mValueStack.pop();
    return mCurrentValue;
  }
}
