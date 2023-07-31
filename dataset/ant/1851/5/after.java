class PlaceHold {
  private String getAlgoName(String classname) {
    ModifiedSelector sel = new ModifiedSelector();
    sel.setProject(getProject());
    sel.addClasspath(testclasses);
    sel.setAlgorithmClass(classname);
    sel.validate();
    String s1 = sel.toString();
    int posStart = s1.indexOf("algorithm=") + 10;
    int posEnd = s1.indexOf(" comparator=");
    String algo = s1.substring(posStart, posEnd);
    if (algo.startsWith("<")) {
      algo = algo.substring(1);
    }
    if (algo.endsWith(">")) {
      algo = algo.substring(0, algo.length() - 1);
    }
    return algo;
  }
}
