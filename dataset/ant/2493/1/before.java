class PlaceHold {
  protected void run(final String classname, final ArrayList args) throws TaskException {
    final CommandlineJava java = new CommandlineJava();
    java.setClassname(classname);
    final int size = args.size();
    for (int i = 0; i < size; i++) {
      final String arg = ((String) (args.get(i)));
      java.createArgument().setValue(arg);
    }
    run(java);
  }
}
