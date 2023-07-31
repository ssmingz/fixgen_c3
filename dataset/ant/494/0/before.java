class PlaceHold {
  public synchronized void removeAspectHandler(final String name, final AspectHandler handler)
      throws TaskException {
    final AspectHandler entry = ((AspectHandler) (m_aspectMap.remove(name)));
    if (null == entry) {
      throw new TaskException(("No such aspect with name '" + name) + "'");
    }
    rebuildArrays();
  }
}
