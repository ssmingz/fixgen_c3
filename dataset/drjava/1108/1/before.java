class PlaceHold {
  public Node createNode(String path, Node n, boolean overwrite) {
    if (n == null) {
      n = _document;
    }
    while (path.indexOf('/') > (-1)) {
      Node child = null;
      String nodeName = path.substring(0, path.indexOf('/'));
      path = path.substring(path.indexOf('/') + 1);
      child = n.getFirstChild();
      while (child != null) {
        if (child.getNodeName().equals(nodeName)) {
          n = child;
          break;
        }
        child = child.getNextSibling();
      }
      if (child == null) {
        child = _document.createElement(nodeName);
        n.appendChild(child);
        n = child;
      }
    }
    String nodeName;
    if (path.indexOf('.') > (-1)) {
      nodeName = path.substring(0, path.indexOf('.'));
    } else {
      if (path.length() == 0) {
        throw new XMLConfigException("Cannot set node with empty name");
      }
      nodeName = path;
    }
    Node child = null;
    if (nodeName.length() > 0) {
      if (overwrite) {
        child = n.getFirstChild();
        while (child != null) {
          if (child.getNodeName().equals(nodeName)) {
            n = child;
            break;
          }
          child = child.getNextSibling();
        }
        if (child == null) {
          child = _document.createElement(nodeName);
          n.appendChild(child);
          n = child;
        }
      } else {
        child = _document.createElement(nodeName);
        n.appendChild(child);
        n = child;
      }
    }
    if (path.indexOf('.') > (-1)) {
      if (!(n instanceof Element)) {
        throw new XMLConfigException(
            ("Node " + n.getNodeName()) + " should be an element so it can contain attributes");
      }
      return n;
    } else if (overwrite) {
      child = n.getFirstChild();
      while (child != null) {
        Node temp = child.getNextSibling();
        n.removeChild(child);
        child = temp;
      }
      return n;
    } else {
      return child;
    }
  }
}
