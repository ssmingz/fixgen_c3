class PlaceHold {
  public int execute(final TaskContext context) throws TaskException {
    validate();
    try {
      final ExecOutputHandler handler = buildOutputHandler(context);
      final ExecManager execManager = ((ExecManager) (context.getService(ExecManager.class)));
      final ExecMetaData metaData = buildExecMetaData(context, execManager);
      logExecDetails(metaData, context);
      final int returnCode = execManager.execute(metaData, handler, m_timeout);
      checkReturnCode(returnCode);
      return returnCode;
    } catch (final Exception e) {
      final String message = REZ.getString("execute.failed.error", m_command.getExecutable());
      throw new TaskException(message, e);
    }
  }
}
