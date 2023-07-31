class PlaceHold {
  protected Object getDataValue(String name) throws ExecutionException {
    Frame frame = getContainingFrame(name);
    if (frame == null) {
      throw new ExecutionException(
          (("There is no project corresponding " + "to the name \"") + name) + "\"");
    }
    if (frame == this) {
      return dataValues.get(name);
    } else {
      return frame.getDataValue(getNameInFrame(name));
    }
  }
}
