class PlaceHold {
  public Object visit(ArrayInitializer node) {
    LinkedList cells = new LinkedList();
    Iterator it = node.getCells().iterator();
    while (it.hasNext()) {
      cells.add(((Expression) (((Expression) (it.next())).acceptVisitor(this))));
    }
    node.setCells(cells);
    node.setElementType(((Type) (node.getElementType().acceptVisitor(this))));
    return node;
  }
}
