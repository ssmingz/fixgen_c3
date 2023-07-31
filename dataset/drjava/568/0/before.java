class PlaceHold {
  private void _showCompilerError(String msg, File f) {
    CompilerError[] errors = new CompilerError[1];
    errors[0] = new CompilerError(f, -1, -1, msg, false);
    _javadocErrorModel = new CompilerErrorModel<CompilerError>(errors, _getter);
    _notifier.javadocEnded(false, null, false);
  }
}
