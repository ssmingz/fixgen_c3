class PlaceHold {
  private Expression makeArray(ArrayType arrayType, Iterable<? extends Expression> elements) {
    Thunk<Class<?>> erasedType = erasedClass(arrayType);
    TypeName tn = TypeUtil.makeEmptyTypeName();
    NodeProperties.setType(tn, arrayType.ofType());
    ArrayInitializer init = new ArrayInitializer(CollectUtil.makeList(elements));
    NodeProperties.setType(init, arrayType);
    NodeProperties.setErasedType(init, erasedType);
    Expression result =
        new ArrayAllocation(
            tn, new ArrayAllocation.TypeDescriptor(new ArrayList<Expression>(0), 1, init, 0, 0));
    NodeProperties.setType(result, arrayType);
    NodeProperties.setErasedType(result, erasedType);
    return result;
  }
}
