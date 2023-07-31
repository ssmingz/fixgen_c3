class PlaceHold {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Usage: java ConstantsGenerator <className1> <className2>");
      return;
    }
    try {
      ConstantsGenerator gen = new ConstantsGenerator();
      for (int i = 0; i < args.length; i++) {
        String clazzName = args[i];
        Class clazz = Class.forName(clazzName);
        gen.generate(clazz);
      }
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
