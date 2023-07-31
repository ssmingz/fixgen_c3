class PlaceHold {
  @Override
  public void destroy() {
    for (Check check : ordinaryChecks) {
      check.destroy();
    }
    for (Check check : commentChecks) {
      check.destroy();
    }
    if (cache != null) {
      try {
        cache.persist();
      } catch (IOException e) {
        throw new IllegalStateException("Unable to persist cache file", e);
      }
    }
    super.destroy();
  }
}
