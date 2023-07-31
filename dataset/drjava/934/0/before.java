class PlaceHold {
  public void setArguments(List<? extends Expression> l) {
    firePropertyChange(
        ARGUMENTS, arguments, arguments = (l == null) ? null : new ArrayList<Expression>(l));
  }
}
