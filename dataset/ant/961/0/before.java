class PlaceHold {
  protected void compile(final File[] compileList) throws TaskException {
    final Commandline cmd = new Commandline();
    setupModernJavacCommand(cmd, compileList);
    final String[] args = cmd.getArguments();
    final Class compilerClass;
    try {
      compilerClass = Class.forName("com.sun.tools.javac.Main");
    } catch (ClassNotFoundException exc) {
      throw new TaskException("Could not find the javac compiler.", exc);
    }
    try {
      final Object compiler = compilerClass.newInstance();
      final Class[] paramTypes = new Class[] {args.getClass()};
      final Method compile = compilerClass.getMethod("compile", paramTypes);
      final Object[] params = new Object[] {args};
      final Integer result = ((Integer) (compile.invoke(compiler, params)));
      if (result.intValue() != 0) {
        throw new TaskException("Javac finished with non-zero return code.");
      }
    } catch (final TaskException exc) {
      throw exc;
    } catch (final Exception exc) {
      throw new TaskException("Could not start javac compiler", exc);
    }
  }
}
