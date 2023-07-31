class PlaceHold {
  protected Object getDataValue(String name) throws ExecutionException {
    Frame frame = getContainingFrame(name);
    if (frame == null) {
      return getOverrideProperty(name);
    }
    if (frame == this) {
      return dataValues.get(name);
    } else {
      return frame.getDataValue(getNameInFrame(name));
    }
  }
}
