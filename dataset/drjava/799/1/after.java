class PlaceHold {
  private void _recreateCompiler() {
    StickyClassLoader loader =
        new StickyClassLoader(_newLoader, getClass().getClassLoader(), _useOldLoader);
    try {
      Class<?> c = loader.loadClass(_className);
      _realCompiler = CompilerRegistry.createCompiler(c);
      _realCompiler.setBuildDirectory(_buildDir);
      _realCompiler.setExtraClassPath(File.pathSeparator + _extraClassPath.toString());
      _realCompiler.setWarningsEnabled(_warningsEnabled);
      boolean allowAssertions = DrJava.getConfig().getSetting(RUN_WITH_ASSERT).booleanValue();
      _realCompiler.setAllowAssertions(allowAssertions);
      String compilerClass = _realCompiler.getClass().getName();
    } catch (Throwable t) {
    }
  }
}
