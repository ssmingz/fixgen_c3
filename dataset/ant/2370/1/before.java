class PlaceHold {
  protected Object makeObject(UnknownElement ue, RuntimeConfigurable w) {
    Object o = makeTask(ue, w, true);
    if (o == null) {
      o = project.createDataType(ue.getTag());
    }
    if (o == null) {
      throw getNotFoundException("task or type", ue.getTag());
    }
    return o;
  }
}
