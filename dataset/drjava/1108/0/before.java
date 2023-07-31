class PlaceHold {
  public Node set(String path, String value, Node n, boolean overwrite) {
    int dotPos = path.lastIndexOf('.');
    Node node;
    if (dotPos == 0) {
      node = n;
    } else {
      node = createNode(path, n, overwrite);
    }
    if (dotPos >= 0) {
      Element e = ((Element) (node));
      e.setAttribute(path.substring(dotPos + 1), value);
    } else {
      node.appendChild(_document.createTextNode(value));
    }
    return node;
  }
}
