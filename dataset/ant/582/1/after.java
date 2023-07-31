class PlaceHold {
  protected boolean isDataValueSet(String name) throws ExecutionException {
    Frame frame = getContainingFrame(name);
    if (frame == null) {
      return isOverrideSet(name);
    }
    if (frame == this) {
      return dataValues.containsKey(name);
    } else {
      return frame.isDataValueSet(getNameInFrame(name));
    }
  }
}
