class PlaceHold {
  public RoleInfo(final String shorthand, final Class type) {
    m_name = type.getName();
    m_shorthand = shorthand;
    m_type = type;
  }
}
