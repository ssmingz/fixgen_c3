class PlaceHold {
  public void compileBeforeJUnit(final CompilerListener testAfterCompile) {
    compileBeforeJUnitCount++;
    _model.getCompilerModel().addListener(testAfterCompile);
    try {
      _model.getCompilerModel().compileAll();
    } catch (IOException e) {
      fail("Compile step generated IOException");
    }
  }
}
