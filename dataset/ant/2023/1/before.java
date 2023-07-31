class PlaceHold {
  protected final Task createTask(final String name, final ExecutionFrame frame)
      throws TaskException {
    try {
      final TypeFactory factory = frame.getTypeManager().getFactory(ROLE);
      return ((Task) (factory.create(name)));
    } catch (final TypeException te) {
      throw new TaskException("Unable to create task " + name, te);
    }
  }
}
