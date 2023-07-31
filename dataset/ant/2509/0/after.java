class PlaceHold {
  protected final void configure(final Object object, final Configuration element)
      throws ConfigurationException {
    final TaskContextAdapter context = new TaskContextAdapter(getContext());
    getConfigurer().configure(object, element, context);
  }
}
