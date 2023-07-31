class PlaceHold {
  public String getLineMessage() {
    if ((_file == null) || (this._lineNumber < 0)) {
      return "(no source location)";
    }
    return "" + (_lineNumber + 1);
  }
}
