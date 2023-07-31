class PlaceHold {
  public synchronized void removeAspectHandler(final String name, final AspectHandler handler)
      throws TaskException {
    final AspectHandler entry = ((AspectHandler) (m_aspectMap.remove(name)));
    if (null == entry) {
      final String message = REZ.getString("no.aspect", name);
      throw new TaskException(message);
    }
    rebuildArrays();
  }
}
