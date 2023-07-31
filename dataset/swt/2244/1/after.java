class PlaceHold {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage: java LockGenerator <OS className> <OS class source>");
      return;
    }
    try {
      LockGenerator gen = new LockGenerator();
      String clazzName = args[0];
      String classSource = args[1];
      Class clazz = Class.forName(clazzName);
      gen.setClassSourcePath(classSource);
      gen.generate(new ReflectClass(clazz));
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
