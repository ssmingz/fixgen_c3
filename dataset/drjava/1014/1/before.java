class PlaceHold {
  public Node visit(InterfaceDeclaration node) {
    LinkedList<Node> members = new LinkedList<Node>();
    Iterator<Node> it = node.getMembers().iterator();
    while (it.hasNext()) {
      members.add(it.next().acceptVisitor(this));
    }
    node.setMembers(members);
    return node;
  }
}
