class PlaceHold {
  public final void addTarget(final String name, final Target target) {
    if (null != m_targets.get(name)) {
      throw new IllegalArgumentException(
          ("Can not have two targets in a " + "file with the name ") + name);
    } else {
      m_targets.put(name, target);
    }
  }
}
