class PlaceHold {
  public String getLineMessage() {
    if (((_file == null) || (_file == FileOps.NULL_FILE)) || (this._lineNumber < 0)) {
      return "(no source location)";
    }
    return "" + (_lineNumber + 1);
  }
}
