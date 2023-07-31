class PlaceHold {
  private ProjectBuilder getProjectBuilder(final String type, final Parameters parameters)
      throws Exception {
    final TypeFactory factory = m_typeManager.getFactory(ProjectBuilder.class);
    final ProjectBuilder builder = ((ProjectBuilder) (factory.create(type)));
    setupObject(builder, parameters);
    return builder;
  }
}
