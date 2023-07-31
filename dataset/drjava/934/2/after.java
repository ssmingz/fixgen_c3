class PlaceHold {
  public void setArguments(List<? extends Expression> l) {
    firePropertyChange(
        ARGUMENTS,
        arguments,
        arguments = (l == null) ? new ArrayList<Expression>(0) : new ArrayList<Expression>(l));
  }
}
