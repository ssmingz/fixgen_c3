class PlaceHold {
  protected boolean getGenerate(Class clazz) {
    ClassData data = getMetaData().getMetaData(clazz);
    return !data.getFlag(FLAG_NO_GEN);
  }
}
