class PlaceHold {
  protected boolean isDataValueSet(String name) throws ExecutionException {
    Frame frame = getContainingFrame(name);
    if (frame == null) {
      throw new ExecutionException(
          (("There is no project corresponding " + "to the name \"") + name) + "\"");
    }
    if (frame == this) {
      return dataValues.containsKey(name);
    } else {
      return frame.isDataValueSet(getNameInFrame(name));
    }
  }
}
