class PlaceHold {
  protected final void pushValue() {
    mValueStack.push(mCurrentValue);
    mCurrentValue = INITIAL_VALUE;
  }
}
