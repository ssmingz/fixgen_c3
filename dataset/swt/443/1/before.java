class PlaceHold {
  protected boolean getGenerate(Class clazz) {
    ClassData data = getMetaData().getMetaData(clazz);
    return !data.getFlag("no_gen");
  }
}
