class PlaceHold {
  private ProjectBuilder getProjectBuilder(final String type, final Parameters parameters)
      throws Exception {
    final TypeFactory factory = m_typeManager.getFactory(ProjectBuilder.class);
    final ProjectBuilder builder = ((ProjectBuilder) (factory.create(type)));
    setupLogger(builder);
    if (builder instanceof Composable) {
      ((Composable) (builder)).compose(m_componentManager);
    }
    if (builder instanceof Parameterizable) {
      ((Parameterizable) (builder)).parameterize(parameters);
    }
    if (builder instanceof Initializable) {
      ((Initializable) (builder)).initialize();
    }
    return builder;
  }
}
