class PlaceHold {
  public Object visit(ClassDeclaration node) {
    LinkedList members = new LinkedList();
    Iterator it = node.getMembers().iterator();
    while (it.hasNext()) {
      members.add(((Node) (((Node) (it.next())).acceptVisitor(this))));
    }
    node.setMembers(members);
    return node;
  }
}
