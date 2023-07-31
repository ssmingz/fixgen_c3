class PlaceHold {
  public Node visit(ArrayInitializer node) {
    LinkedList<Expression> cells = new LinkedList<Expression>();
    Iterator<Expression> it = node.getCells().iterator();
    while (it.hasNext()) {
      cells.add(((Expression) (it.next().acceptVisitor(this))));
    }
    node.setCells(cells);
    node.setElementType(((Type) (node.getElementType().acceptVisitor(this))));
    return node;
  }
}
