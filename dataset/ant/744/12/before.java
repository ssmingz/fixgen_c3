class PlaceHold {
  public void execute() {
    final Map properties = getContext().getProperties();
    final Iterator iterator = properties.keySet().iterator();
    while (iterator.hasNext()) {
      final String key = ((String) (iterator.next()));
      final Object value = properties.get(key);
      getContext().warn((key + "=") + value);
    }
  }
}
