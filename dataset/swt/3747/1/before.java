class PlaceHold {
  public static void main(String[] args) {
    JNIGeneratorApp gen = new JNIGeneratorApp();
    if (args.length > 0) {
      gen.setMainClass(args[0]);
      if (args.length > 1) {
        gen.setOutputDir(args[1]);
      }
      if (args.length > 2) {
        gen.setClasspath(args[2]);
      }
    } else {
      gen.setMainClass(getDefaultMainClass());
    }
    gen.generate();
  }
}
