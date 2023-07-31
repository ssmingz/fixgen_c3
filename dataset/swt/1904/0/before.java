class PlaceHold {
  TreeItem addChild(Node node, TreeItem superItem, Hashtable extras) {
    String name = node.getNodeName();
    if (name.equals("#text")) {
      return null;
    }
    TreeItem parentItem = null;
    TreeItem[] items = superItem.getItems();
    for (int i = 0; i < items.length; i++) {
      if (name.equals(items[i].getData())) {
        parentItem = items[i];
        break;
      }
    }
    if (parentItem == null) {
      parentItem = new TreeItem(superItem, SWT.NONE);
      parentItem.setData(name);
      parentItem.setText(getPrettyText(name));
    }
    NamedNodeMap attributes = node.getAttributes();
    TreeItem item = new TreeItem(parentItem, SWT.NONE);
    Node nameAttrib = getNameAttrib(attributes);
    String text = (nameAttrib != null) ? nameAttrib.getNodeValue() : name;
    item.setText(text);
    Node extra = ((Node) (extras.get(getKey(node))));
    if (extra != null) {
      NamedNodeMap extraAttributes = extra.getAttributes();
      Node gen = extraAttributes.getNamedItem("swt_gen");
      if ((gen != null) && gen.getNodeValue().equals("true")) {
        item.setChecked(true);
      }
    }
    for (int i = 0; i < attributes.getLength(); i++) {
      Node attrib = attributes.item(i);
      text = attrib.getNodeName();
      if (attrib.equals(nameAttrib)) {
        continue;
      }
      int columnIndex = getColumnFor(text);
      item.setText(columnIndex, attrib.getNodeValue());
    }
    NodeList childNodes = node.getChildNodes();
    for (int i = 0; i < childNodes.getLength(); i++) {
      addChild(childNodes.item(i), item, extras);
    }
    return item;
  }
}
