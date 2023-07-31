class PlaceHold {
  public Object visit(ClassAllocation node) {
    node.setCreationType(((Type) (node.getCreationType().acceptVisitor(this))));
    LinkedList arguments = null;
    if (node.getArguments() != null) {
      arguments = new LinkedList();
      Iterator it = node.getArguments().iterator();
      while (it.hasNext()) {
        arguments.add(((Expression) (((Expression) (it.next())).acceptVisitor(this))));
      }
    }
    node.setArguments(arguments);
    LinkedList members = new LinkedList();
    Iterator it = node.getMembers().iterator();
    while (it.hasNext()) {
      members.add(((Node) (((Node) (it.next())).acceptVisitor(this))));
    }
    node.setMembers(members);
    return node;
  }
}
