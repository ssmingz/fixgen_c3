class PlaceHold {
  public void testCompilerArg() {
    String[] args = javac.getCurrentCompilerArgs();
    assertNotNull(args);
    assertEquals("no args", 0, args.length);
    Javac.ImplementationSpecificArgument arg = javac.createCompilerArg();
    String ford = "Ford";
    String prefect = "Prefect";
    String testArg = (ford + " ") + prefect;
    arg.setValue(testArg);
    args = javac.getCurrentCompilerArgs();
    assertEquals("unconditional single arg", 1, args.length);
    assertEquals(testArg, args[0]);
    arg.setCompiler("jikes");
    args = javac.getCurrentCompilerArgs();
    assertNotNull(args);
    assertEquals("implementation is jikes but build.compiler is null", 0, args.length);
    project.setProperty("build.compiler", "classic");
    args = javac.getCurrentCompilerArgs();
    assertNotNull(args);
    assertEquals("implementation is jikes but build.compiler is classic", 0, args.length);
    project.setProperty("build.compiler", "jikes");
    args = javac.getCurrentCompilerArgs();
    assertEquals("both are jikes", 1, args.length);
    assertEquals(testArg, args[0]);
    arg.setLine(testArg);
    args = javac.getCurrentCompilerArgs();
    assertEquals("split at space", 2, args.length);
    assertEquals(ford, args[0]);
    assertEquals(prefect, args[1]);
  }
}
