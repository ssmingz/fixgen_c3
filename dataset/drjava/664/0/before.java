class PlaceHold {
  public Object visit(SuperMethodCall node) {
    LinkedList arguments = null;
    if (node.getArguments() != null) {
      arguments = new LinkedList();
      Iterator it = node.getArguments().iterator();
      while (it.hasNext()) {
        arguments.add(((Expression) (((Expression) (it.next())).acceptVisitor(this))));
      }
    }
    node.setArguments(arguments);
    return node;
  }
}
