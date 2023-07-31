class PlaceHold {
  public static void main(String[] args) {
    if (args.length < 3) {
      System.out.println(
          "Usage: java CleanupConstants <OS className> <class source> <src path1> <src path2>");
      return;
    }
    try {
      CleanupConstants gen = new CleanupConstants();
      String clazzName = args[0];
      String classSource = args[1];
      String[] sourcePath = new String[args.length - 2];
      System.arraycopy(args, 2, sourcePath, 0, sourcePath.length);
      Class clazz = Class.forName(clazzName);
      gen.setSourcePath(sourcePath);
      gen.setClassSourcePath(classSource);
      gen.generate(new ReflectClass(clazz));
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
