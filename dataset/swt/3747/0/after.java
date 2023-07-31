class PlaceHold {
  public static void main(String[] args) {
    JNIGeneratorApp gen = new JNIGeneratorApp();
    if (args.length > 0) {
      gen.setMainClassName(args[0]);
      if (args.length > 1) {
        gen.setOutputDir(args[1]);
      }
    } else {
      gen.setMainClassName(JNIGeneratorApp.getDefaultMainClass());
    }
    JNIGeneratorAppUI ui = new JNIGeneratorAppUI(gen);
    ui.open();
    ui.run();
  }
}
