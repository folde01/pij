public class AddAndSeekScript {
  public static void main(String[] args) {
    AddAndSeekScript aass = new AddAndSeekScript();
    aass.launch();
  }  

  public void launch() {
    IntegerTreeNode top = new IntegerTreeNode(15);
    top.add(4);
    top.add(49);
    top.add(10);
    top.add(44);
    top.add(3);
    top.add(51);
    top.add(1);
    top.add(2);
    top.toString2();

    // System.out.println(top.contains(6));
    // System.out.println(top.getMax());
    // System.out.println(top.getMin());
  }
}
