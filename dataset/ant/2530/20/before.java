class PlaceHold {
  public Object nextElement() throws NoSuchElementException {
    Object o = null;
    try {
      o = conditions.elementAt(currentElement++);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new NoSuchElementException();
    }
    if (o instanceof ProjectComponent) {
      ((ProjectComponent) (o)).setProject(getProject());
    }
    return o;
  }
}
