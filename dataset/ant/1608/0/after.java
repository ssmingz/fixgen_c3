class PlaceHold {
  public final void addTarget(final String name, final Target target) {
    if (null != m_targets.get(name)) {
      final String message = REZ.getString("duplicate-target.error", name);
      throw new IllegalArgumentException(message);
    } else {
      m_targets.put(name, target);
    }
  }
}
