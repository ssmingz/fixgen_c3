class ASTField {
  public ASTField(
      ASTClass declaringClass,
      String source,
      FieldDeclaration field,
      VariableDeclarationFragment fragment) {
    this.declaringClass = declaringClass;
    name = fragment.getName().getIdentifier();
    modifiers = field.getModifiers();
    start = field.getStartPosition();
    Javadoc doc = field.getJavadoc();
    List tags = null;
    if (doc != null) {
      tags = doc.tags();
      for (Iterator iterator = tags.iterator(); iterator.hasNext(); ) {
        TagElement tag = ((TagElement) (iterator.next()));
        if ("@field".equals(tag.getTagName())) {
          String data = tag.fragments().get(0).toString();
          setMetaData(data);
          break;
        }
      }
    }
    type = new ASTType(declaringClass.resolver, field.getType(), fragment.getExtraDimensions());
    type64 = this.type;
    if (GEN64) {
      String s =
          source.substring(field.getStartPosition(), field.getStartPosition() + field.getLength());
      if (type.isType("int") && (s.indexOf("int /*long*/") != (-1))) {
        type64 = new ASTType("J");
      } else if (type.isType("float") && (s.indexOf("float /*double*/") != (-1))) {
        type64 = new ASTType("D");
      } else if (type.isType("[I")
          && ((s.indexOf("int /*long*/") != (-1)) || (s.indexOf("int[] /*long[]*/") != (-1)))) {
        type64 = new ASTType("[J");
      } else if (type.isType("[F")
          && ((s.indexOf("float /*double*/") != (-1))
              || (s.indexOf("float[] /*double[]*/") != (-1)))) {
        type64 = new ASTType("[D");
      } else if (type.isType("long") && (s.indexOf("long /*int*/") != (-1))) {
        type = new ASTType("I");
      } else if (type.isType("double") && (s.indexOf("double /*float*/") != (-1))) {
        type = new ASTType("F");
      } else if (type.isType("[J")
          && ((s.indexOf("long /*int*/") != (-1)) || (s.indexOf("long[] /*int[]*/") != (-1)))) {
        type = new ASTType("[I");
      } else if (type.isType("[D")
          && ((s.indexOf("double /*float*/") != (-1))
              || (s.indexOf("double[] /*float[]*/") != (-1)))) {
        type = new ASTType("[F");
      }
    }
  }
}
