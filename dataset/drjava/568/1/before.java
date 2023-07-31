class PlaceHold {
  private void _distributeErrors(CompilerError[] errors) throws IOException {
    resetCompilerErrors();
    _compilerErrorModel = new CompilerErrorModel<CompilerError>(errors, _getter);
  }
}
