class PlaceHold {
  public void generate() {
    if (classes == null) {
      return;
    }
    generateMetaData(getCopyrightKey());
    generateMetaData(getIncludesKey());
    sort(classes);
    for (int i = 0; i < classes.length; i++) {
      Class clazz = classes[i];
      ClassData data = getMetaData().getMetaData(clazz);
      if (data.getFlag("cpp")) {
        isCPP = true;
      }
    }
    for (int i = 0; i < classes.length; i++) {
      Class clazz = classes[i];
      if (getGenerate(clazz)) {
        generate(clazz);
      }
    }
  }
}
