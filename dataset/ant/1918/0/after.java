class PlaceHold {
  protected final void configure(final Object object, final String name, final String value)
      throws ConfigurationException {
    final TaskContextAdapter context = new TaskContextAdapter(getContext());
    getConfigurer().configure(object, name, value, context);
  }
}
