class PlaceHold {
  protected final Task createTask(final String name, final ExecutionFrame frame)
      throws TaskException {
    try {
      final TypeFactory factory = frame.getTypeManager().getFactory(ROLE);
      return ((Task) (factory.create(name)));
    } catch (final TypeException te) {
      final String message = REZ.getString("no-create.error", name);
      throw new TaskException(message, te);
    }
  }
}
