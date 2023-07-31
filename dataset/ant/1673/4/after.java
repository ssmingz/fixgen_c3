class PlaceHold {
  protected final void addUnresolvedValue(final String name, final String value) {
    try {
      final Object objectValue = getContext().resolveValue(value.toString());
      final String name1 = m_prefix + name;
      getContext().setProperty(name1, objectValue);
    } catch (final TaskException te) {
      final String message = REZ.getString("loadprop.bad-resolve.error", name, value);
      getLogger().info(message, te);
    }
  }
}
