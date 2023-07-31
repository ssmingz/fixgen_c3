class PlaceHold {
  public void testCompilerArgForForkAndExtJavac() {
    Javac.ImplementationSpecificArgument arg = javac.createCompilerArg();
    String ford = "Ford";
    String prefect = "Prefect";
    String testArg = (ford + " ") + prefect;
    arg.setValue(testArg);
    arg.setImplementation("extJavac");
    javac.setFork(true);
    String[] args = javac.getCurrentCompilerArgs();
    assertEquals("both are forked javac", 1, args.length);
    assertEquals(testArg, args[0]);
  }
}
