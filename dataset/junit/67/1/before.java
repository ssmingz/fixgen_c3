class PlaceHold {
  @Override
  protected boolean matchesSafely(T item) {
    return fMatcher.matches(item.getMessage());
  }
}
