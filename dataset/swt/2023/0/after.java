class PlaceHold {
  public NSArray recentSearches() {
    long result = OS.objc_msgSend(this.id, sel_recentSearches);
    return result != 0 ? new NSArray(result) : null;
  }
}
