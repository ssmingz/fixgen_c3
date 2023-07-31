class PlaceHold {
  public static void main(String[] args) {
    LanguageLevelConverter llc = new LanguageLevelConverter(JavaVersion.CURRENT);
    File[] files = new File[args.length];
    for (int i = 0; i < args.length; i++) {
      files[i] = new File(args[i]);
    }
    Pair<LinkedList<JExprParseException>, LinkedList<Pair<String, JExpressionIF>>> result =
        llc.convert(files);
    System.out.println((result.getFirst().size() + result.getSecond().size()) + " errors.");
    for (JExprParseException p : result.getFirst()) {
      System.out.println(p);
    }
    for (Pair<String, JExpressionIF> p : result.getSecond()) {
      System.out.println((p.getFirst() + " ") + p.getSecond().getSourceInfo());
    }
  }
}
